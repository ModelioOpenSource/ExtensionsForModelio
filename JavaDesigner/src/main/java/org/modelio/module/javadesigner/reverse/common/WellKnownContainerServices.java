package org.modelio.module.javadesigner.reverse.common;

import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Encapsulate genericity translation for collections and binding.
 */
public class WellKnownContainerServices {
    protected static HashMap<String, ContainerDescription> knownContainers = new HashMap<>();

    /**
     * Load the configuration file that describes well known generic containers
     * and their description
     * @param configurationFile : the XML configuration file
     */
    public static void loadContainerConfiguration(final File configurationFile) {
        try {
            WellKnownContainerServices.clear();
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
        
            Document doc = builder.parse(configurationFile);
            NodeList containersElements = doc.getElementsByTagName("container");
        
            for (int i = 0; i < containersElements.getLength(); i++) {
                ContainerDescription description;
                Element containerTag = (Element) containersElements.item(i);
        
                String cName = containerTag.getAttribute("name");
                String cPackage = containerTag.getAttribute("package");
                String cCard = containerTag.getAttribute("cardinality");
                int tIndex = Integer.parseInt(containerTag
                        .getAttribute("type-index"));
                int kIndex;
                try {
                    kIndex = Integer.parseInt(containerTag
                            .getAttribute("key-index"));
                    description = new ContainerDescription(cName, cCard,
                            tIndex, kIndex);
                } catch (NumberFormatException e) {
                        // no defined key index...
                        description = new ContainerDescription(cName, cCard, tIndex);
                    }
                knownContainers.put(cName, description);
                knownContainers.put(cPackage + "." + cName, description);
            }
        
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    /**
     * Tell if the type name is a well known generic container
     * @param typeName : type name to check. Can be a short name as well as a full
     * qualified name
     * @return true if type name matches with a well known generic container,
     * false otherwise
     */
    public static boolean isWellKnownContainer(final String typeName) {
        return knownContainers.containsKey(typeName);
    }

    /**
     * Return the cardinality value of a well known container
     * @param typeName a short or full qualified name of the container
     * @return the cardinality string (as set in containers.xml) or "1" if
     * 'typeName' is not well known
     */
    public static String getWellKnownContainerMultiplicityMax(final String typeName) {
        ContainerDescription cd = knownContainers.get(typeName);
        if (cd != null) {
            return cd.getCardinality();
        }
        return "1";
    }

    /**
     * This operation is used to reinitialize all lists in order to launch
     * several reverses.
     */
    public static void clear() {
        knownContainers.clear();
    }

    /**
     * Class describing a generic container, with its informations to retrieve
     * the real type and (if exists) the key type.
     */
    public static class ContainerDescription {
        /**
         * Name of the container
         */
        private String containerName;

        /**
         * Cardinality of the container
         */
        private String cardinality;

        /**
         * index to find the content type in a generic declaration
         */
        private int typeIndex;

        /**
         * index to find the key type in a generic declaration. Is -1 if the
         * container doesn't have a key type.
         */
        private int keyIndex;

        ContainerDescription(final String containerName, final String cardinality, final int typeIndex) {
            this.containerName = containerName;
            this.cardinality = cardinality;
            this.typeIndex = typeIndex;
            this.keyIndex = -1;
        }

        ContainerDescription(final String containerName, final String cardinality, final int typeIndex, final int keyIndex) {
            this.containerName = containerName;
            this.cardinality = cardinality;
            this.typeIndex = typeIndex;
            this.keyIndex = keyIndex;
        }

        /**
         * @return the key index.
         */
        public int getKeyIndex() {
            return this.keyIndex;
        }

        /**
         * @return the type index
         */
        public int getTypeIndex() {
            return this.typeIndex;
        }

        /**
         * @return the container name
         */
        public String getContainerName() {
            return this.containerName;
        }

        /**
         * @return the cardinality
         */
        public String getCardinality() {
            return this.cardinality;
        }

    }

}
