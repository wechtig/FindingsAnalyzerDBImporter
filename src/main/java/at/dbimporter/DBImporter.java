package at.dbimporter;

import checkstyle.CheckstyleError;
import checkstyle.CheckstyleFile;
import checkstyle.CheckstyleRoot;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.ProjectConfig;
import service.DBObjectConverter;
import db.Finding;
import org.bson.Document;
import pmd.PmdError;
import pmd.PmdFile;
import pmd.PmdRoot;

import java.time.LocalDate;

public class DBImporter {

    private String databaseName;
    private String collectionName;
    private String dbUrl;
    private String projectName;

    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private MongoCollection<Document> configurations;
    private static final String CONFIG_COLLECTION = "configurations";

    public DBImporter(String dbUrl, String databaseName, String collectionName, String projectName) {
        this.dbUrl = dbUrl;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        this.projectName = projectName;
    }

    public void dbImport(CheckstyleRoot checkstyleRoot) {
        for(CheckstyleFile checkstyleFile : checkstyleRoot.getFiles()) {
            System.out.println("Files"+checkstyleRoot.getFiles().size());
            for(CheckstyleError checkstyleError : checkstyleFile.getErrors()) {
                System.out.println("Files"+checkstyleFile.getErrors().size());

                Finding finding = new Finding();
                finding.setFile(checkstyleFile.getName());
                finding.setLine(checkstyleError.getLine()+"");
                finding.setMessage(checkstyleError.getMessage());
                finding.setSeverity(checkstyleError.getSeverity());
                finding.setSource(checkstyleError.getSource());
                finding.setDate(LocalDate.now());
                finding.setProject(projectName);

                Document findingDocument = DBObjectConverter.ConvertTrackToDocument(finding);
                collection.insertOne(findingDocument);
            }
        }
    }


    public void dbImport(PmdRoot pmdRoot) {
        System.out.println(pmdRoot.getFiles().size());


        for(PmdFile pmdFile : pmdRoot.getFiles()) {
            System.out.println("Files" + pmdRoot.getFiles().size());

            for(PmdError pmdError: pmdFile.getErrors()) {
                System.out.println("Errors: "+pmdFile.getErrors().size());

                Finding finding = new Finding();
                finding.setFile(pmdFile.getName());
                finding.setLine(pmdError.getLine());
                finding.setMessage(pmdError.getMessage());
                finding.setSeverity(pmdError.getPriority()+"");
                finding.setSource(pmdError.getRule());
                finding.setDate(LocalDate.now());
                finding.setProject(projectName);

                Document findingDocument = DBObjectConverter.ConvertTrackToDocument(finding);
                collection.insertOne(findingDocument);
            }
        }
    }

    public void connect() {
        mongoClient = new MongoClient(new MongoClientURI(dbUrl));
        db = mongoClient.getDatabase(databaseName);
        collection = db.getCollection(collectionName);
        configurations = db.getCollection(CONFIG_COLLECTION);
    }

    public void checkAndAddConfiguration() {
        BasicDBObject query = new BasicDBObject();
        query.put("name", projectName);
        Document document = configurations.find(query).first();

        if(document == null || document.isEmpty()) {
            ProjectConfig projectConfig = new ProjectConfig();
            projectConfig.setName(projectName);
            configurations.insertOne(DBObjectConverter.convertProjectConfigToDocument(projectConfig));
        }
    }
}
