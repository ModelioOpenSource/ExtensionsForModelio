package org.modelio.module.javadesigner.ant;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class AntTargetLoader extends DefaultHandler {
    private List<String> targets;

    public AntTargetLoader() {
        super ();
    }

    @Override
    public void startDocument() throws SAXException {
        this.targets = new ArrayList<> ();
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (qName.equals ("target")) {
            this.targets.add (attributes.getValue("name"));
        }
    }

    /**
     * Treat validation errors as fatal
     */
    @Override
    public void error(final SAXParseException e) throws SAXParseException {
        throw e;
    }

    public List<String> getTargets() {
        return this.targets;
    }

}
