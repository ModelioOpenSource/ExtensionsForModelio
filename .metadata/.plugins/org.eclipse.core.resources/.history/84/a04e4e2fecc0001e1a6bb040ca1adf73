package org.modelio.module.javadesigner.xmlreverse.model.serialization;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.modelio.module.javadesigner.xmlreverse.model.ObjectFactory;


public class ModelMarshaller {

    public ModelMarshaller() {
    }


    public void save(final Object jaxb_element, final File file, final String charsetName) {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName(),ObjectFactory.class.getClassLoader());
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
            FileOutputStream fos = new FileOutputStream(file);
            try (PrintStream out = new PrintStream(new BufferedOutputStream(fos), true, charsetName)) {
                marshaller.marshal(jaxb_element, out);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
