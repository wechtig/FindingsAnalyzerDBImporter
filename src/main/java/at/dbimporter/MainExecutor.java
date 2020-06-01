package at.dbimporter;

import checkstyle.CheckstyleError;
import checkstyle.CheckstyleFile;
import checkstyle.CheckstyleRoot;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import pmd.PmdRoot;
import pmd.RootGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Mojo(name = "import", defaultPhase = LifecyclePhase.INSTALL)
public class MainExecutor extends AbstractMojo {

    @Parameter(property = "dbName",defaultValue = "findings")
    private String dbName;

    @Parameter(property = "dbUrl",defaultValue = "mongodb://localhost:27017")
    private String dbUrl;

    @Parameter(property = "xmlPath", defaultValue = "target/bugs.xml")
    private String xmlFile;


    @Parameter(property = "collection", defaultValue = "checkstyleFindings")
    private String collection;

    public void execute() throws MojoExecutionException {

        getLog().info("Import in DB " + dbUrl + " " + dbName +" gestartet um " + LocalDateTime.now());

        MavenProject project = (MavenProject) getPluginContext().get("project");
        String xmlPath = project.getBasedir().getAbsolutePath() + "/" + xmlFile;
        DBImporter dbImporter = new DBImporter(dbUrl, dbName, collection, project.getName());
        dbImporter.connect();
        dbImporter.checkAndAddConfiguration();

        if(xmlPath.contains("checkstyle")) {
            CheckstyleRoot checkstyleRoot = generateCheckstyleObject(xmlPath);
            getLog().info("Import in DB " + dbUrl + "/" + dbName + " started");
            dbImporter.dbImport(checkstyleRoot);
            getLog().info("Import in DB finished");
        } else if(xmlPath.contains("pmd")) {
            RootGenerator rootGenerator = new RootGenerator();
            PmdRoot pmdRoot = rootGenerator.generatePmdObject(xmlPath);
            getLog().info("Import in DB " + dbUrl + "/" + dbName + " started");
            dbImporter.dbImport(pmdRoot);
            getLog().info("Import in DB finished");
        }

    }

    private CheckstyleRoot generateCheckstyleObject(String xmlFile) {
        JAXBContext jaxbContext;
        CheckstyleRoot checkstyleRoot = null;
        try
        {
            jaxbContext = JAXBContext.newInstance(CheckstyleRoot.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            checkstyleRoot = (CheckstyleRoot) jaxbUnmarshaller.unmarshal(new File(xmlFile));
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return checkstyleRoot;
    }



}