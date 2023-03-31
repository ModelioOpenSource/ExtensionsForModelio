package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackageImport;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.vcore.smkernel.mapi.MObject;


public class PackageImportStrategy extends ModelElementStrategy implements IReverseLink<JaxbPackageImport,PackageImport,ModelElement,Package> {
    
    public PackageImportStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public PackageImport getCorrespondingElement(final JaxbPackageImport jaxb_element, final ModelElement source, final Package target, final IReadOnlyRepository repository) {
        if(source instanceof Operation){
            Operation linkSource = (Operation)source;
        
            // Search for a package import between those elements
            for (PackageImport link: linkSource.getOwnedPackageImport()) {
                Package targetNameSpace = link.getImportedPackage();
                if (targetNameSpace.equals(target)) {
                    return link;
                }
            }
        } else {
            NameSpace linkSource = (NameSpace)source;
        
            // Search for a package import between those elements
            for (PackageImport link: linkSource.getOwnedPackageImport()) {
                Package targetNameSpace = link.getImportedPackage();
                if (targetNameSpace.equals(target)) {
                    return link;
                }
            }
        }
        return this.model.createPackageImport();
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbPackageImport jaxb_element, final PackageImport modelio_element, final ModelElement source, final Package target, final IReadOnlyRepository repository) {
        String name = jaxb_element.getName();
        if(name != null)
            modelio_element.setName(name);
        
        if(source instanceof Operation){
            modelio_element.setImportingOperation((Operation)source);
        }else{
            modelio_element.setImportingNameSpace((NameSpace)source);
        }
        
        modelio_element.setImportedPackage(target);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbPackageImport jaxb_element, final PackageImport modelio_element, final IReadOnlyRepository repository) {
        // Nothing to do
    }

    
    @Override
    public void deleteSubElements(final JaxbPackageImport jaxb_element, final PackageImport modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
