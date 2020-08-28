# FindingsAnalyzerBac2
__Year:__ 2020  

## Ausgangssituation und Idee
Der FindingsAnalyzerDBImporter ist ein Bestandteil des FindingsAnalyzerBac2: https://github.com/wechtig/FindingsAnalyzerBac2

## Ziele
* Importieren von den Ergebnissen der Statischen Code Analyse in die Datenbank 
* Unterstützung eines individuellen Einsatzes von verschieden Code-Analyse-Plugins

## Installation
__Vorraussetzungen__  
1. Apache Maven: https://maven.apache.org/download.cgi  
2. MongoDB-Datenbank
    
__Setup__  
1. Den Sourcecode als .zip oder über GIT herunterladen
2. Erstellen eines Admin-Users in der Datenbank oder über die Weboberfläche. Dieser User ist das erste Teammitglied eines Projekts und kann dann andere einladen.
3. Den Importer mit maven im Ziel-Projekt hinzufügen. Beispiel-Konfiguration für das Plugin Checkstyle (https://maven.apache.org/plugins/maven-checkstyle-plugin/)
            <dbUrl>mongodb://localhost:27017</dbUrl>
            <dbName>findings</dbName>
            <xmlPath>target/checkstyle-result.xml</xmlPath>
            <collection>checkstyleFindings</collection>
            ...
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>3.1.1</version>
            </plugin>
            <plugin>
                <groupId>at.dbimporterbac2</groupId>
                <artifactId>at.dbimporterbac2</artifactId>
                <version>1.0-SNAPSHOT</version>
                <configuration>
                    <dbName>${dbName}</dbName>
                    <dbUrl>${dbUrl}</dbUrl>
                    <xmlFile>${xmlPath}</xmlFile>
                    <collection>${collection}</collection>
                </configuration>
                <executions>
                    <execution>
                        <phase>install</phase>
                        <goals>
                            <goal>import</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
Anmerkung: Werden andere Plugins verwendet, muss der Pfad der xml-Ausgabe im Property "xmlPath" angegeben werden.

__Starten__
1. Der Import wird beim Befehl "mvn clean install" durchgeführt.

## Weitere Möglichkeiten
* Import der Daten als Projekt-Auswahl in der Weboberfläche.
* Lösung für Plugins, welche keine XML-Ausgabe unterstützen.
* Sichere Erstellung des Admin-User 

