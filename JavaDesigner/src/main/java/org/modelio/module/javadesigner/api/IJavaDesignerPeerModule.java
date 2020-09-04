package org.modelio.module.javadesigner.api;

import java.io.File;
import java.util.Collection;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;

public interface IJavaDesignerPeerModule extends IPeerModule {
    public static final String MODULE_NAME = "JavaDesigner";

    void generate(final NameSpace element);

    void generate(final Collection<NameSpace> elements);

    void generateAntFile(final Artifact artifact);

    String executeTarget(final Artifact artifact, final String target);

    void updateModel(final Collection<NameSpace> elements);

    void generate(final NameSpace element, final boolean withGUI);

    void generate(final Collection<NameSpace> elements, final boolean withGUI);

    void generateAntFile(final Artifact artifact, final boolean withGUI);

    String executeTarget(final Artifact artifact, final String target, final boolean withGUI);

    void updateModel(final Collection<NameSpace> elements, final boolean withGUI);

    File getFilename(final NameSpace element);

    /**
     * Update all the accessors corresponding to this element (type, card, name...).
     * @throws ElementNotUniqueException
     * @param theAttribute The model element to synchronize accessors from.
     * @param createNewAccessors Indicates whereas the method should create accessors if necessary, or only synchronize the existing ones.
     * @return true if something changed in the model.
     */
    boolean updateAccessors(final Attribute theAttribute, final boolean createNewAccessors) throws CustomException, ElementNotUniqueException, ExtensionNotFoundException;

    /**
     * Update all the accessors corresponding to this element (type, card, name...).
     * @throws ElementNotUniqueException
     * @param theAssociationEnd The model element to synchronize accessors from.
     * @param createNewAccessors Indicates whereas the method should create accessors if necessary, or only synchronize the existing ones.
     * @return true if something changed in the model.
     */
    boolean updateAccessors(final AssociationEnd theAssociationEnd, final boolean createNewAccessors) throws CustomException, ElementNotUniqueException, ExtensionNotFoundException;

    /**
     * Check all operations, and deletes getters and setters that are no more linked to Attributes/AssociationEnds.
     * @return True if at least one operation was deleted.
     */
    boolean deleteAccessors(final Classifier theClassifier);

    void generateJavaDoc(final Package element, final boolean withGUI);

}
