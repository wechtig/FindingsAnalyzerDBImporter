package pmd;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RootGenerator {
    public PmdRoot generatePmdObject(String xmlFile) {
        JAXBContext jaxbContext = null;
        PmdRoot pmdRoot = null;
        try {
            jaxbContext = JAXBContext.newInstance(PmdRoot.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            pmdRoot = (PmdRoot) jaxbUnmarshaller.unmarshal( new File(xmlFile) );

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return pmdRoot;
    }
}
