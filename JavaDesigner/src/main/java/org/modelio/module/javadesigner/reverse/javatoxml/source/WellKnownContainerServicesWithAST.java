package org.modelio.module.javadesigner.reverse.javatoxml.source;

import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.common.WellKnownContainerServices;

/**
 * Encapsulate genericity translation for collections and binding.
 */
class WellKnownContainerServicesWithAST extends WellKnownContainerServices {
    /**
     * Get the generic parameter of rank index
     * @param type : the root generic type
     * @param index : index of the generic parameter
     * @return : a type node representing the generic parameter at rank 'index' or null if 'type' is not generic
     */
    private static ASTTree getGenericArgumentByIndex(final ASTTree type, final int index) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected AST node type on entry of WellKnownContainerServices.getNodeAtIndex";
        ASTTree typeargs = TypeAstServices.getTypeArgs(type);
        if (typeargs == null) {
            return null;
        }
        ASTTree result = (ASTTree) typeargs.getChild(index);
        assert (result.getType() == JavaParser.TYPE) : "Internal error: unexpected AST node type on return of WellKnownContainerServices.getNodeAtIndex";
        return result;
    }

    /**
     * Return the type that is the key in a generic structure that admit a key
     * as generic parameter (ie Map)
     * @param type : the root type node
     * @return : the key type node or null if not found.
     */
    protected static ASTTree getQualifierType(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        ASTTree result = null;
        ContainerDescription desc = WellKnownContainerServices.knownContainers.get(TypeAstServices
                .getTypeCanonicalIdentifier(type));
        if (desc == null) {
            return null;
        }
        int index = desc.getKeyIndex();
        if (TypeAstServices.isGenericType(type) && index != -1) {
            result = getGenericArgumentByIndex(type, index);
        }
        return result;
    }

    /**
     * Return the type that is the payload in a generic structure with keyed
     * access (ie Map)
     * @param type : the root type node
     * @return The payload type node or null if type is not a well known
     * container or if it is a well known container but used
     * without generic parameter (Java < 1.5).
     */
    protected static ASTTree getRealDestinationType(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        ContainerDescription desc = knownContainers.get(TypeAstServices
                .getTypeCanonicalIdentifier(type));
        if (desc == null) {
            // Not a well known container, therefore no real destination
            return null;
        }
        int index = desc.getTypeIndex();
        return getGenericArgumentByIndex(type, index);
    }

}
