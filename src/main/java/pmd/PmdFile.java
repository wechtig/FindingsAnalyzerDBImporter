package pmd;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "file")
@XmlAccessorType(XmlAccessType.FIELD)
public class PmdFile {

    @XmlAttribute
    protected String name;

    @XmlElement(name = "violation")
    private List<PmdError> errors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PmdError> getErrors() {
        return errors;
    }

    public void setErrors(List<PmdError> errors) {
        this.errors = errors;
    }
}
