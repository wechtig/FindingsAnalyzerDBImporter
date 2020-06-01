package pmd;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "pmd", namespace = "http://pmd.sourceforge.net/report/2.0.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class PmdRoot {

    @XmlElement(name = "file")
    private List<PmdFile> files = new ArrayList<>();

    public List<PmdFile> getFiles() {
        return files;
    }

    public void setFiles(List<PmdFile> files) {
        this.files = files;
    }
}
