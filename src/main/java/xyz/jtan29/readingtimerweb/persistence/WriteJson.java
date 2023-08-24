package xyz.jtan29.readingtimerweb.persistence;


import xyz.jtan29.readingtimerweb.model.ListOfText;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes a representation of ListOfText data into a json file
public class WriteJson {
    private String destinationFile;
    private PrintWriter writer;
    private static final int TAB = 4;


    // MODIFIES: this
    // EFFECTS: creates new WriteJson object with the given destination file
    public WriteJson(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: starts the writer
    public void start() throws FileNotFoundException {
        writer = new PrintWriter(destinationFile);
    }

    // MODIFIES: this
    // EFFECTS: writes the information from the given ListOfText to the Json file
    public void writeFile(ListOfText txt) {
        JSONObject json = txt.toJson();
        saveFile(json.toString(TAB));

    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves the file using the provided data
    private void saveFile(String json) {
        writer.print(json);
    }
}

