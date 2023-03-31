package org.modelio.module.javadesigner.xmlreverse.revers;

import java.util.HashSet;
import java.util.Set;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;


class Cleaner extends DefaultModelVisitor {
    
    private Repository repository;

    
    private Set<MObject> elements_todelete;

    
    public Set<MObject> getResult() {
        return  this.elements_todelete;
    }

    
    public Cleaner(final Repository repository) {
        this.repository = repository;
        this.elements_todelete = new HashSet<>();
    }

    
    @Override
    public Object visitAssociationEnd(final AssociationEnd element) {
        for(Attribute qualifier : element.getQualifier()){
            if(!this.repository.isRecordedElement(qualifier)){
        
                this.elements_todelete.add(qualifier);
            }
        }
        return super.visitAssociationEnd(element);
    }

    
    @Override
    public Object visitClassifier(final Classifier element) {
        for(Attribute att : element.getOwnedAttribute()){
            if(!this.repository.isRecordedElement(att)){
                if(!this.elements_todelete.contains(att))
                    this.elements_todelete.add(att);
            }
        }
        
        for(AssociationEnd end : element.getOwnedEnd()){
            if((end.isNavigable()) && end.getOpposite() != null){
                AssociationEnd opposite = end.getOpposite();        
                if(!this.repository.isRecordedElement(end) && !this.repository.isRecordedElement(opposite)){
                    if(!this.elements_todelete.contains(end))
                        this.elements_todelete.add(end);
                }
            }
        }
        
        for(Operation op : element.getOwnedOperation()){
            if(!this.repository.isRecordedElement(op)){
                if(!this.elements_todelete.contains(op))
                    this.elements_todelete.add(op);
            }
        }
        return super.visitClassifier(element);
    }

    
    @Override
    public Object visitEnumeration(final Enumeration element) {
        for(EnumerationLiteral note : element.getValue()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        return super.visitEnumeration(element);
    }

    
    @Override
    public Object visitUmlModelElement(final UmlModelElement element) {
        for(Note note : element.getDescriptor()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(TaggedValue note : element.getTag()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(Constraint note : element.getConstraintDefinition()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(Dependency note : element.getDependsOnDependency()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        return super.visitUmlModelElement(element);
    }

    
    @Override
    public Object visitModelTree(final ModelTree element) {
        for(ModelTree note : element.getOwnedElement()){
            if(note instanceof Package ||
                    note instanceof Class ||
                    note instanceof Signal ||
                    note instanceof Interface||
                    note instanceof DataType||
                    note instanceof Enumeration){
                if(!this.repository.isRecordedElement(note)){
        
                    this.elements_todelete.add(note);
                }
            }
        
        }
        return super.visitModelTree(element);
    }

    
    @Override
    public Object visitNameSpace(final NameSpace element) {
        for(Generalization note : element.getParent()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(InterfaceRealization note : element.getRealized()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(TemplateParameter note : element.getTemplate()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(Instance note : element.getDeclared()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(ElementImport note : element.getOwnedImport()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(PackageImport note : element.getOwnedPackageImport()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        return super.visitNameSpace(element);
    }

    
    @Override
    public Object visitOperation(final Operation element) {
        for(RaisedException note : element.getThrown()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        for(Parameter note : element.getIO()){
            if(!this.repository.isRecordedElement(note)){
        
                this.elements_todelete.add(note);
            }
        }
        
        Parameter returned = element.getReturn();
        if(returned != null){
            if(!this.repository.isRecordedElement(returned)){
                if(!this.elements_todelete.contains(returned))
                    this.elements_todelete.add(returned);
            }
        }
        
        for (TemplateParameter templateParameter : element.getTemplate()) {
            if(!this.repository.isRecordedElement(templateParameter)){
                if(!this.elements_todelete.contains(templateParameter))
                    this.elements_todelete.add(templateParameter);
            }
        }
        return super.visitOperation(element);
    }

}
