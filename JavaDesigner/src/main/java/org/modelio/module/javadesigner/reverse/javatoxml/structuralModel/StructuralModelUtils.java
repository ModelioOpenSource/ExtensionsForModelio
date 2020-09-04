package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassTemplateParameter;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;

/**
 * This class defines all the basic services to create and find structural elements.
 */
public class StructuralModelUtils {
    /**
     * Add a PackageDef in the structural model, given its short name and its parent as a structural model elements
     * If it already exists, it is returned
     * @param aPackageShortName : the short name of the package to be inserted
     * @param parent : its parent or null if packageName is a top package
     * @return : the created or retrieved PackageDef matching packageName
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException when a structural element already exists in the parent but is not a PackageDef
     */
    public static PackageDef addPackage(final String aPackageShortName, final StructuralTree parent) throws NameCollisionException {
        assert aPackageShortName.indexOf(".") == -1;
        assert parent != null;
        PackageDef pDef = null;
        StructuralTree existing = parent.getOwned(aPackageShortName);
        if (existing == null) {
            // New package : add it
            pDef = new PackageDef(aPackageShortName);
            parent.addOwned(pDef);
        } else {
            // Existing package check it is a package
            if (existing instanceof PackageDef) {
                pDef = (PackageDef) existing;
            } else {
                throw new NameCollisionException(aPackageShortName);
            }
        }
        return pDef;
    }

    /**
     * Add a PackageDef hierarchy in the structural model given its full qualified name. If it already exists it is returned.
     * All the intermediate packages are added if needed in the structural model.
     * @throws NameCollisionException
     * @param aFqnPackageName full qualified name of the package to create
     * @return existing or new PackageDef matching aFqnPackageName
     */
    public static PackageDef addPackageHierarchy(final String aFqnPackageName, final PackageDef root) throws NameCollisionException {
        PackageDef pack = root;
        for (String n : splitFullQualifiedName(aFqnPackageName)) {
            pack = addPackage(n, pack);
        }
        return pack;
    }

    /**
     * Get a PackageDef given its name and its parent
     * @param aPackageShortName name of the package to be retrieved (no dot)
     * @param aParent the direct parent of the package or root package
     * @return the matching PackageDef instance or null if not found
     */
    static PackageDef getPackage(final String aPackageShortName, final PackageDef aParent) {
        assert aParent != null;
        Map<String, StructuralTree> lookuplist = aParent.getOwned();
        StructuralTree nd = lookuplist.get(aPackageShortName);
        return (nd instanceof PackageDef)? (PackageDef)nd : null;
    }

    /**
     * Add a new class under its parent.
     * @param className
     * @throws NameCollisionException
     * @param parent @return
     */
    public static ClassifierDef addClass(final String className, final ClassifierDefKind kind, final StructuralTree parent) {
        assert parent != null;
        // parent exists (package or class)
        if (parent.getOwned().containsKey(className)) {
            // Can't have the same classifier under the same parent
            final StructuralTree def = parent.getOwned().get(className);
            if (def instanceof ClassifierDef) {
                return (ClassifierDef) def;
            }
        }
        ClassifierDef cDef = new ClassifierDef(className, kind);
        parent.addOwned(cDef);
        return cDef;
    }

    /**
     * find a StructuralTree from its full qualified name
     * Because it takes a full qualified name , there no need to take inheritance into account.
     * @param aFullQualifiedName full qualified name to search for
     * @return the StructuralTree matching the fqn or null if not found
     */
    static StructuralTree getStructuralTree(final String aFullQualifiedName, final PackageDef root) {
        Map<String, StructuralTree> searchList = root.getOwned();
        StructuralTree foundNs = null;
        for (String n : splitFullQualifiedName(aFullQualifiedName)) {
            foundNs = searchList.get(n);
            if (foundNs != null) {
                searchList = foundNs.getOwned();
            } else {
                // Not found !
                break;
            }
        }
        return foundNs;
    }

    /**
     * Retrieve a ClassifierDef given its name and a start element.
     * The search is done in the start element and in its inherited elements
     * @param aName : either a short or a partial qualified name
     * @param startElement :
     * @return
     */
    static ClassifierDef getClassifier(final String aName, final StructuralTree startElement) {
        assert startElement != null;
        List<StructuralTree> tmp = new ArrayList<>();
        tmp.add(startElement);
        List<? extends StructuralTree> candidates = tmp;
        List<ClassifierDef> result = null;
        for (String n : splitFullQualifiedName(aName)) {
            result = getAllClassifiers(n, candidates, startElement);
            if (result.isEmpty()) {
                // no result : its useless to continue
                break;
            }
            candidates = result;
        }
        // If more than one, then it's either an error or a type hide another. Anyway the first element is the best choice
        return (result == null || result.isEmpty()? null: result.get(0));
    }

    /**
     * Return a list of Classifier that matches a short name in the children of a given element. Take into account the inheritance
     * hierarchy of the start element. The list is sorted with the nearer element first.
     * @param aShortName the short name to be searched
     * @param possibleOwners list of possible direct owner of aShortName
     * @param aRoot root context of the search. From where all this begins
     * @return list of owners of aShortName
     */
    private static List<ClassifierDef> getAllClassifiers(final String aShortName, final Collection<? extends StructuralTree> possibleOwners, final StructuralTree aRoot) {
        // short name only !
        assert aShortName.indexOf('.') == -1;
        List<ClassifierDef> resultList = new ArrayList<>();
        // Collect matching elements
        for (StructuralTree  owner : possibleOwners) {
            if (owner != null) {
                Map<String,StructuralTree> candidates = owner.getOwned();
                StructuralTree elt = candidates.get(aShortName);
                if (elt != null && elt instanceof ClassifierDef) {
                    if (aRoot instanceof PackageDef || (aRoot instanceof ClassifierDef && isVisible((ClassifierDef)elt, (ClassifierDef)aRoot))) {
                        resultList.add((ClassifierDef) elt);
                    }
                }
            }
        }
        // Then recurse on inheritance tree : two steps because of the ordering of the result list
        Set<ClassifierDef> possibleInherited = new HashSet<>();
        for (StructuralTree  owner : possibleOwners) {
            if (owner instanceof ClassifierDef) {
                List<ClassifierDef> inherit = ((ClassifierDef)owner).getInherits();
                if (inherit != null) {
                    possibleInherited.addAll(inherit);
                }
            }
        }
        
        if (!possibleInherited.isEmpty()) {
            resultList.addAll(getAllClassifiers(aShortName, possibleInherited, aRoot));
        }
        return resultList;
    }

    /**
     * Tell if anElt is visible from aRoot
     * @param anElt the element whose visibility is checked
     * @param aRoot the point from where anElt should be visible
     * @return
     */
    private static boolean isVisible(final ClassifierDef anElt, final ClassifierDef aRoot) {
        switch (anElt.getVisibility()) {
        case PUBLIC:
        case UNKNOWN:
            return true;
        case PACKAGE:
            PackageDef pack = aRoot.getPackage();
            return anElt.getPackage() == pack;
        case PRIVATE:
            return anElt.isLooselyOwnedBy(aRoot);
        case PROTECTED:
            if (anElt.isLooselyOwnedBy(aRoot)) {
                return true;
            } else {
                StructuralTree owner = anElt.getOwner();
                if (owner instanceof ClassifierDef) {
                    return  aRoot.isHeirOf((ClassifierDef)owner);
                }
            }
            //$FALL-THROUGH$
        default:
            break;
        }
        return false;
    }

    private static String[] splitFullQualifiedName(final String aFullQualifiedName) {
        return aFullQualifiedName.split("\\.");
    }

    static ClassTemplateParameter addClassTemplateParameterDef(final String parname, final ClassifierDef classdef) {
        assert classdef != null;
        ClassTemplateParameter tmplpar = new ClassTemplateParameter(parname);
        classdef.addTemplateParameter(tmplpar);
        return tmplpar;
    }

    public static ClassTemplateParameter getClassTemplateParameter(final String aName, final StructuralTree owner) {
        assert owner != null;
        if (owner instanceof ClassifierDef) {
            ClassifierDef classdef = (ClassifierDef) owner;
            Map<String, ClassTemplateParameter> pars = classdef.getTemplateParameters();
            if (pars != null) {
                return pars.get(aName);
            }
        }
        return null;
    }

}
