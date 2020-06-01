
import at.dbimporter.DBImporter;
import pmd.PmdRoot;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;

public class ImportTest {
    public static void main(String[] args) {
        JAXBContext jaxbContext = null;
        PmdRoot checkstyleDoc = null;
        try {
            jaxbContext = JAXBContext.newInstance(PmdRoot.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            checkstyleDoc = (PmdRoot) jaxbUnmarshaller.unmarshal( new File("C:\\Users\\luweh\\Downloads\\code-smells-master (1)\\code-smells-master\\target\\pmd.xml") );


            DBImporter dbImporter = new DBImporter("mongodb://localhost:27017", "findings", "findings", "pse-project");
            dbImporter.connect();
            dbImporter.dbImport(checkstyleDoc);

        } catch (JAXBException e) {
            e.printStackTrace();
       }

      /*  MongoClient mongoClient;
        MongoDatabase db;
        MongoCollection<Document> collection;

        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        db = mongoClient.getDatabase("findings");
        collection = db.getCollection("checkstyleFindings");

        DBImporter dbImporter = new DBImporter("mongodb://localhost:27017", "findings", "checkstyleFindings", "meintestProject");
        Finding finding = new Finding();
        finding.setDate(LocalDate.now());
        finding.setFile("Sender.java");
        finding.setSeverity("error");
        finding.setMessage("Not closed!");
        finding.setSource("com.puppycrawl.tools.checkstyle.checks.blocks.AvoidNestedBlocksCheck");
        finding.setLine(72+"");
        finding.setProject("testanalyseproject");

        Document doc = DBObjectConverter.ConvertTrackToDocument(finding);
        collection.insertOne(doc);

        MainExecutor mainExecutor = new MainExecutor();*/
    }
}
