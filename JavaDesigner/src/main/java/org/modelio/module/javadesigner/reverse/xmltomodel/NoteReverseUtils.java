package org.modelio.module.javadesigner.reverse.xmltomodel;

import java.util.ArrayList;
import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;

public class NoteReverseUtils {
    private static NoteReverseUtils INSTANCE = new NoteReverseUtils ();

    private NoteReverseUtils() {
    }

    public static NoteReverseUtils getInstance() {
        return INSTANCE;
    }

    public String reverseJavadoc(final IModelingSession session, final String baseJavaDoc, final Operation modelio_element, final String moduleName, final String noteType, final IReadOnlyRepository repository) throws ExtensionNotFoundException {
        List<Parameter> params = new ArrayList<> (modelio_element.getIO ());
        
        String[] tmpZones = baseJavaDoc.split ("@");
        if (tmpZones.length == 0) { // No @something to parse...
            return baseJavaDoc;
        } else {
            // The first value is the operation Javadoc
            String operationJavaDoc = tmpZones[0].trim();
        
            List<String> zones = new ArrayList<>();
            for (int i = 1 ; i < tmpZones.length ; i++) {
                String zone = tmpZones[i];
                if (zone.startsWith ("param ") ||
                        zone.startsWith ("return ") ||
                        zone.startsWith ("throws ") ||
                        zone.startsWith ("see ")) {
                    zones.add(zone);
                } else {
                    if (zones.size()>0) {
                        String lastZone = zones.remove(zones.size() - 1);
                        lastZone += "@" + zone;
                        zones.add(lastZone);
                    } else if (!operationJavaDoc.endsWith("{")) {
                        operationJavaDoc += "\n@" + zone.trim();
                    } else {
                        operationJavaDoc += "@" + zone.trim();
                    }
                }
            }
        
        
            for (String zone : zones) {
                String annotationJavaDoc = zone;
                boolean modelUpdated = false;
        
                if (annotationJavaDoc.startsWith ("param ")) {
                    annotationJavaDoc = annotationJavaDoc.substring (6);
        
                    int paramNameIndex = getIndexOfFirstSpaceOrNL(annotationJavaDoc);
        
                    if (paramNameIndex > -1) {
                        String paramName = annotationJavaDoc.substring (0, paramNameIndex);
                        String paramJavaDoc = annotationJavaDoc.substring (paramNameIndex + 1, annotationJavaDoc.length ());
                        paramJavaDoc = paramJavaDoc.trim ();
                        if (!paramJavaDoc.isEmpty()) {
                            for (Parameter param : modelio_element.getIO ()) {
                                if (JavaDesignerUtils.getJavaName (param).equals (paramName)) {
                                    param.putNoteContent (moduleName, noteType, paramJavaDoc);
        
                                    params.remove(param);
        
                                    modelUpdated = true;
                                    break;
                                }
                            }
                        }
                    }
                } else if (annotationJavaDoc.startsWith ("return ")) {
                    annotationJavaDoc = annotationJavaDoc.substring (7);
                    modelUpdated = updateReturnJavadoc (modelio_element, annotationJavaDoc, moduleName, noteType);
                } else if (annotationJavaDoc.startsWith ("throws ")) {
                    annotationJavaDoc = annotationJavaDoc.substring (7);
                    modelUpdated = updateThrowsJavadoc (session, modelio_element, annotationJavaDoc, moduleName, noteType);
                } else if (annotationJavaDoc.startsWith ("see ")) {
                    annotationJavaDoc = annotationJavaDoc.substring (4);
                    modelUpdated = updateSeeJavadoc (session, modelio_element, annotationJavaDoc, repository);
                }
        
                if (!modelUpdated) {
                    operationJavaDoc += "\n@" + zone.trim();
                }
            }
        
            // Delete javadoc from parameters that haven't been handled
            for (Parameter param : params) {
                param.removeNotes(moduleName, noteType);
            }
        
            return operationJavaDoc.trim();
        }
    }

    public String reverseJavadoc(final IModelingSession session, final String baseJavaDoc, final GeneralClass modelio_element, final IReadOnlyRepository repository) throws ExtensionNotFoundException {
        String[] zones = baseJavaDoc.split ("^@");
        if (zones.length <= 1) {
            return baseJavaDoc;
        } else {
            // The first value is the operation Javadoc
            String operationJavaDoc = zones[0];
        
            for (int i = 1 ; i < zones.length ; i++) {
                String annotationJavaDoc = zones[i];
                boolean modelUpdated = false;
        
                if (annotationJavaDoc.startsWith ("see")) {
                    annotationJavaDoc = annotationJavaDoc.substring (4);
                    modelUpdated = updateSeeJavadoc (session, modelio_element, annotationJavaDoc, repository);
                }
        
                if (!modelUpdated) {
                    operationJavaDoc += "\n@" + zones[i];
                }
            }
        
            return operationJavaDoc;
        }
    }

    private boolean updateSeeJavadoc(final IModelingSession session, final ModelElement modelio_element, final String initialJavaDoc, final IReadOnlyRepository repository) throws ExtensionNotFoundException {
        String javaDoc = initialJavaDoc;
        String targetFullName;
        String type = "";
        
        if (javaDoc.contains ("[op]")) { // See link towards an operation
            int opIndex = javaDoc.indexOf ("[op]");
            int paramStart = javaDoc.indexOf ("(");
        
            // If there is a parenthesis, this is a see with arguments
            if ((paramStart > 0) && (paramStart < opIndex)) {
                int paramEnd = javaDoc.indexOf (")");
        
                // Line is badly constructed, unable to parse
                if (paramEnd == -1) {
                    return false;
                }
                // Get the parameters
                String params = javaDoc.substring (paramStart + 1, paramEnd).trim ();
        
                int firstCommaIndex = params.indexOf (",");
                int firstSpaceIndex = getIndexOfFirstSpaceOrNL (params);
                if (firstSpaceIndex > -1 &&
                        (firstCommaIndex > -1 || firstSpaceIndex < firstCommaIndex)) { // If there is a space, this is a
                    // "type and name" see link
                    type = "Generate the object name with argument types and names";
                } else {
                    type = "Generate the object name with argument types";
                }
        
                targetFullName = javaDoc.substring (0, paramStart).trim ();
            } else {
                targetFullName = javaDoc.substring (0, opIndex).trim ();
            }
        
            javaDoc = javaDoc.substring (opIndex + 4).trim ();
        } else if (javaDoc.contains ("[at]")) { // See link towards an attribute or association end
            int atIndex = javaDoc.indexOf ("[at]");
            // Line is badly constructed, unable to parse
            if (atIndex == -1) {
                return false;
            }
            targetFullName = javaDoc.substring (0, atIndex).trim ();
            javaDoc = javaDoc.substring (atIndex + 4).trim ();
        
        } else { // See link towards another Java element
            int spaceIndex = getIndexOfFirstSpaceOrNL(javaDoc);
            // Line is badly constructed, unable to parse
            if (spaceIndex == -1) {
                return false;
            }
            targetFullName = javaDoc.substring (0, spaceIndex).trim ();
            javaDoc = javaDoc.substring (spaceIndex + 1).trim ();
        }
        
        // Search the targeted element in the dependency list
        ModelElement foundElement = null;
        for (Dependency theDependency : modelio_element.getDependsOnDependency ()) {
            if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.SEEJAVADOC)) {
                ModelElement dependsOnElement = theDependency.getDependsOn ();
        
                // Match element with name
                if ((JavaDesignerUtils.getJavaName (dependsOnElement).equals (targetFullName) || JavaDesignerUtils.getFullJavaName (session, dependsOnElement).equals (targetFullName))) {
                    foundElement = theDependency;
                    break;
                }
            }
        }
        
        if (foundElement == null) {
            // Find the element with its namespace
            ModelElement targetedElement = (ModelElement) repository.getElementByNameSpace (targetFullName, null, session);
            if (targetedElement != null) {
                foundElement = session.getModel().createDependency (modelio_element, targetedElement, null);
                ModelUtils.addStereotype(foundElement, JavaDesignerStereotypes.SEEJAVADOC);
            } else {
                return false;
            }
        }
        
        // Set the content
        String noteContent = type + "\n" + javaDoc;
        foundElement.putNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.DEPENDENCY_SEEJAVADOC, noteContent);
        return true;
    }

    private boolean updateThrowsJavadoc(final IModelingSession session, final Operation modelio_element, final String javaDoc, final String moduleName, final String noteType) throws ExtensionNotFoundException {
        int paramNameIndex = getIndexOfFirstSpaceOrNL(javaDoc);
        if (paramNameIndex > -1) {
            String thrownName = javaDoc.substring (0, paramNameIndex);
            String throwJavaDoc = javaDoc.substring (paramNameIndex + 1, javaDoc.length ());
            throwJavaDoc = throwJavaDoc.trim ();
        
            if (!throwJavaDoc.isEmpty()) {
                // Search element import with <<throw>>
                for (ElementImport theRaisedException : modelio_element.getOwnedImport ()) {
                    if (theRaisedException.isStereotyped(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.ELEMENTIMPORT_THROW)) {
                        NameSpace thrownElement = theRaisedException.getImportedElement ();
                        if (JavaDesignerUtils.getJavaName (thrownElement).equals (thrownName) ||
                                JavaDesignerUtils.getFullJavaName (session, thrownElement).equals (thrownName)) {
                            theRaisedException.putNoteContent (moduleName, noteType, throwJavaDoc);
                            return true;
                        }
                    }
                }
        
                // Search RaisedException links
                for (RaisedException theRaisedException : modelio_element.getThrown ()) {
                    Classifier thrownElement = theRaisedException.getThrownType ();
                    if (JavaDesignerUtils.getJavaName (thrownElement).equals (thrownName) ||
                            JavaDesignerUtils.getFullJavaName (session, thrownElement).equals (thrownName)) {
                        theRaisedException.putNoteContent (moduleName, noteType, throwJavaDoc);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean updateReturnJavadoc(final Operation modelio_element, final String initialJavaDoc, final String moduleName, final String noteType) throws ExtensionNotFoundException {
        String javaDoc = initialJavaDoc.trim ();
        
        if (!javaDoc.isEmpty()) {
            Parameter param = modelio_element.getReturn ();
            if (param != null) {
                param.putNoteContent (moduleName, noteType, javaDoc);
                return true;
            }
        }
        return false;
    }

    public void cleanUpCode(final StringBuilder ret, final String initialContent) {
        String content = initialContent;
        
        // Replace tabs with spaces
        content = content.replaceAll ("\t", "    ");
        
        // Remove trailing whitespaces
        content = content.replaceAll ("[\\s]+$", "");
        
        // Remove empty lines in header
        content = content.replaceAll ("^[\\s]*\n", "");
        
        // Remove blanks before the first line in all lines to keep indent correct
        String[] lines = content.split ("\n");
        if (lines.length > 1) {
            // Remove trailing whitespaces in the first line
            String firstLineTmp = lines[0].replaceAll ("[\\s]+$", "");
            // Try to remove leading whitespaces in the first line
            String firstLine = firstLineTmp.replaceAll ("^[\\s]+", "");
        
            // Different sizes means there are whitespaces at the beginning of the first line
            if (firstLine.length () < firstLineTmp.length ()) {
                // Compute the indent zone to be removed in all lines...
                String indent = firstLineTmp.substring (0, firstLineTmp.length () -
                        firstLine.length ());
                int indexLength = indent.length ();
        
                // Remove indent for all lines
                for (String line : lines) {
                    if (line.startsWith (indent)) {
                        ret.append (line.substring (indexLength));
                    } else {
                        ret.append (line);
                    }
                    ret.append ("\n");
                }
            } else {
                // No indent detected, put the content unchanged
                ret.append (content);
            }
        } else if (lines.length == 1) {
            // Only one line, remove its useless whitespaces
            ret.append (content.trim ());
        }
    }

    public void cleanUpComments(final StringBuilder ret, final String initialContent) {
        String content = initialContent;
        
        // Replace tabs with spaces
        content = content.replaceAll ("\t", "    ");
        
        int startCommentIndex = content.indexOf ("/**");
        // This is a Javadoc comment
        if (startCommentIndex == 0 && content.length() > 4) {
            int endCommentIndex = content.lastIndexOf ("*/");
            content = content.substring (startCommentIndex + 3, endCommentIndex);
            content = content.trim ();
        
            String[] lines = content.split ("\n");
            if (lines.length > 0) {
                for (int i = 0 ; i < lines.length ; i++) {
                    String line = lines[i];
                    line = line.replaceAll ("^[\\s]*\\*", "");
                    line = line.trim ();
                    if (i != 0) {
                        ret.append ("\n");
                    }
                    ret.append (line);
                }
            }
        } else {
            startCommentIndex = content.indexOf ("//");
        
            // This is a simple comment
            if (startCommentIndex == 0) {
                content = content.substring (startCommentIndex + 2, content.length ());
                content = content.trim ();
                if (!content.isEmpty ()) {
                    ret.append (content);
                }
            } else {
                startCommentIndex = content.indexOf ("/*");
                // This is a Javadoc comment
                if (startCommentIndex == 0) {
                    StringBuilder scontent = new StringBuilder(content);
                    // remove trailing '*/'
                    int endCommentIndex = content.lastIndexOf ("*/");
                    scontent.delete(endCommentIndex, endCommentIndex+2);
                    // then remove heading '/*' (in this order to avoid mess up with indexes)
                    scontent.delete(startCommentIndex, startCommentIndex+2);
                    content = scontent.toString();
                    content = content.trim ();
        
                    String[] lines = content.split ("\n");
                    if (lines.length > 0) {
                        for (int i = 0 ; i < lines.length ; i++) {
                            String line = lines[i];
                            line = line.replaceAll ("^[\\s]*\\*?", "");
                            line = line.trim ();
                            if (i != 0) {
                                ret.append ("\n");
                            }
                            ret.append (line);
                        }
                    }
                } else {
                    // No identified comment pattern, keep content as is
                    ret.append (content);
                }
            }
        }
    }

    private int getIndexOfFirstSpaceOrNL(final String zone) {
        int NLIndex = zone.indexOf ("\n");
        int spaceIndex = zone.indexOf (" ");
        
        if (NLIndex > -1 && spaceIndex > -1) {
            return Math.min(NLIndex, spaceIndex);
        } else if (NLIndex > -1) {
            return NLIndex;
        } else {
            return spaceIndex;
        }
    }

}
