package checkstyle;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "file")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckstyleFile {

    @XmlAttribute
    protected String name;

    @XmlElement(name = "error")
    private List<CheckstyleError> errors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CheckstyleError> getErrors() {
        return errors;
    }

    public void setErrors(List<CheckstyleError> errors) {
        this.errors = errors;
    }
}
