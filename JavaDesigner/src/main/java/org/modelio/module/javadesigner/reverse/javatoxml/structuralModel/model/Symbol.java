package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;


/**
 * Symbol interface : it is a string that defines a context for a diagnostic
 * It can be :
 * - a StructuralTree : classifier and package
 * - a TypedElement : attribute, association-end, parameter : anything typed on which a diagnostic can be done (type not found, etc)
 * 
 * Up to now, template parameter (either operation or classifier) and primitive types are not diagnostic symbols.
 */
public interface Symbol {
    String getName();

}
