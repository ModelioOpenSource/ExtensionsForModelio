package org.modelio.module.javadesigner.xmlreverse.model;

import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.IReverseModelVisitor;
import com.modeliosoft.modelio.javadesigner.annotations.objid;


public interface IVisitorElement {
    
    Object accept(final IReverseModelVisitor visitor);

}
