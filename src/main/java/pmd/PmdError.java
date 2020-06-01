package pmd;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "violation")
@XmlAccessorType(XmlAccessType.FIELD)
public class PmdError {
    @XmlAttribute
    protected String rule;

    @XmlAttribute
    protected int priority;

    @XmlAttribute
    protected int beginline;

    @XmlAttribute
    protected int endline;

    @XmlValue
    private String message;

    public String getMessage() {
        return message.replace("\n", "");
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLine() {
        return beginline + "-" + endline;
    }

    public void setBeginline(int beginline) {
        this.beginline = beginline;
    }

    public void setEndline(int endline) {
        this.endline = endline;
    }
}
