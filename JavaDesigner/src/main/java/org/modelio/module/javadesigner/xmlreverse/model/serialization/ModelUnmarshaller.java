package org.modelio.module.javadesigner.xmlreverse.model.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.modelio.module.javadesigner.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.xmlreverse.i18n.Messages;
import org.modelio.module.javadesigner.xmlreverse.model.ObjectFactory;


public class ModelUnmarshaller {

    private IReportWriter reportWriter;


    public ModelUnmarshaller(final IReportWriter reportWriter) {
        this.reportWriter = reportWriter;
    }


    public Object load(final File file, final String charsetName) {
        // creation of the jabcontext able to manage the package
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), charsetName)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName(),ObjectFactory.class.getClassLoader());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return unmarshaller.unmarshal(isr);
        } catch (JAXBException e) {
            if (this.reportWriter != null) {
                this.reportWriter.addError(Messages.getString("Error.parsing.Title"), null, e.toString());
            }
        }catch (FileNotFoundException e) {
            if (this.reportWriter != null) {
                this.reportWriter.addError(Messages.getString("Error.parsing.Title"), null, e.toString());
            }
        } catch (UnsupportedEncodingException e) {
            if (this.reportWriter != null) {
                this.reportWriter.addError(Messages.getString("Error.parsing.Title"), null, e.toString());
            }
        } catch (IOException e) {
            if (this.reportWriter != null) {
                this.reportWriter.addError(Messages.getString("Error.parsing.Title"), null, e.toString());
            }
        }
        return null;
    }

}
