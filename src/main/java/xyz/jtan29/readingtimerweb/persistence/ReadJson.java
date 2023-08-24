package xyz.jtan29.readingtimerweb.persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xyz.jtan29.readingtimerweb.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class ReadJson {
    private String file;

    // MODIFIES: this
    // EFFECTS: constructs a new reader for source file
    public ReadJson(String file) {
        this.file = file;
    }

    // MODIFIES: this
    // EFFECTS: reads the ListOfText from the file
    public ListOfText read() throws IOException {
        String data = readFile(file);
        JSONObject json = new JSONObject(data);
        return parse(json); // stub
    }

    // EFFECTS: turns source file into a string
    private String readFile(String file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        }
        return stringBuilder.toString();
    }

    // EFFECTS: parses the ListOfText from the json object
    private ListOfText parse(JSONObject json) {
        ListOfText texts = new ListOfText();
        addTexts(texts, json);
        return texts;

    }

    // EFFECTS: adds Texts to the ListOfTexts according to the Json object
    private void addTexts(ListOfText texts, JSONObject json) {
        JSONArray textsInArray = json.getJSONArray("texts");
        for (int i = 0; i < textsInArray.length(); i++) {
            JSONObject next = (JSONObject) textsInArray.get(i);
            addTextsHelper(texts, next);
        }
    }

    // EFFECTS: parses each Text from the json object
    private void addTextsHelper(ListOfText texts, JSONObject json) {
        int wordCount = json.getInt("wordCount");
        boolean isComplete = json.getBoolean("isComplete");
        long elapsedTime = json.getLong("elapsedTime");
        String title = json.getString("title");
        String genre = json.getString("genre");
        Genre g = convertGenre(genre);
        Text newText = new Text(wordCount, title, g);
        newText.addTime(elapsedTime);
        newText.setIsComplete(isComplete);
        texts.addText(newText);
    }

    // EFFECTS: converts string value to corresponding Genre
    private Genre convertGenre(String s) {
        switch (s) {
            case ("GENERAL"): {
                return FictionGenre.GENERAL;
            }
            case ("YA"): {
                return FictionGenre.YA;
            }
            case ("GRAPHIC"): {
                return FictionGenre.GRAPHIC;
            }
            case ("CHILDREN"): {
                return FictionGenre.CHILDREN;
            }
            case ("SHORT_STORY"): {
                return FictionGenre.SHORT_STORY;
            }
            case ("OTHER"): {
                return FictionGenre.OTHER;
            }
            default:
                return convertGenreHelper(s);
        }
    }

    // EFFECTS: helper to convert non-fiction genres
    private Genre convertGenreHelper(String s) {
        switch (s) {
            case ("ARTICLE"): {
                return NonFictionGenre.ARTICLE;
            }
            case ("TEXTBOOK"): {
                return NonFictionGenre.TEXTBOOK;
            }
            case ("MEMOIR"): {
                return NonFictionGenre.MEMOIR;
            }
            case ("SELF_HELP"): {
                return NonFictionGenre.SELF_HELP;
            }
            default:
                return NonFictionGenre.NF_OTHER;
        }
    }
}
