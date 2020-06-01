package checkstyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "checkstyle")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckstyleRoot {

    @XmlElement(name = "file")
    private List<CheckstyleFile> files = new ArrayList<>();

    public List<CheckstyleFile> getFiles() {
        return files;
    }

    public void setFiles(List<CheckstyleFile> files) {
        this.files = files;
    }
}
