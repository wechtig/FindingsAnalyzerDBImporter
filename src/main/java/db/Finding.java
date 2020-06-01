package db;

import java.time.LocalDate;

public class Finding {
    private String project;
    private String file;
    private String source;
    private String message;
    private String severity;
    private String line;
    private LocalDate date;

    public String getProject() {
        return project;
    }

    public void setProject(String projectName) {
        this.project = projectName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String fileName) {
        this.file = fileName;
    }

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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
