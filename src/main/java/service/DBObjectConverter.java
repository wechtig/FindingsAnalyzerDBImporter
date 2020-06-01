package service;

import db.Finding;
import db.ProjectConfig;
import org.bson.Document;

public class DBObjectConverter {
    public static Document ConvertTrackToDocument(Finding finding) {
        Document song = new Document();
        song.append("file", finding.getFile());
        song.append("line", finding.getLine());

        song.append("project", finding.getProject());
        song.append("file", finding.getFile());
        song.append("line", finding.getLine());
        song.append("message", finding.getMessage());
        song.append("source", finding.getSource());
        song.append("severity", finding.getSeverity());
        song.append("date", finding.getDate());
        return song;
    }

    public static Document convertProjectConfigToDocument(ProjectConfig projectConfig) {
        org.bson.Document recDoc = new Document();
        recDoc.append("name", projectConfig.getName());
        recDoc.append("users", projectConfig.getUsers());
        recDoc.append("description", projectConfig.getDescription());
        recDoc.append("vcsRepositoryLink", projectConfig.getVcsRepositoryLink());

        return recDoc;
    }
}
