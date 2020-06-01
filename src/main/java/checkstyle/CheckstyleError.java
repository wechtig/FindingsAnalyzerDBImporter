package checkstyle;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckstyleError {
    @XmlAttribute
    protected String source;

    @XmlAttribute
    protected String message;

    @XmlAttribute
    protected String severity;

    @XmlAttribute
    protected int line;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
