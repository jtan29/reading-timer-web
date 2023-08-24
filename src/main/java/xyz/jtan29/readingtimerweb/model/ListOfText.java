package xyz.jtan29.readingtimerweb.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xyz.jtan29.readingtimerweb.persistence.ToWrite;

import java.util.ArrayList;
import java.util.List;

// Representation of a collection of texts
public class ListOfText implements ToWrite {
    private List<Text> texts;

    // MODIFIES: this
    // EFFECTS: creates new ListOfText with empty list of texts
    public ListOfText() {
        texts = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: adds the Text to the list of texts
    public void addText(Text t) {
        texts.add(t);
    }

    // MODIFIES: this
    // EFFECTS: removes the Text from the list of texts
    public void removeText(Text t) {
        texts.remove(t);
    }

    // EFFECTS: returns average reading speed for given genre of texts in list,
    //          or zero if no texts of the genre are found
    public int calcGenreReadingSpeed(Genre g) {
        int totalWordCount = 0;
        int totalElapsedTime = 0;
        for (Text t : texts) {
            if (t.getGenre() == g && t.getIsComplete()) {
                totalWordCount += t.getWordCount();
                totalElapsedTime += t.getElapsedTime();
            }
        }
        if ((totalElapsedTime / Text.SECONDS_PER_MINUTE) > 0) {
            return totalWordCount / (totalElapsedTime / Text.SECONDS_PER_MINUTE);
        } else {
            return totalWordCount;
        }
    }

    // REQUIRES: wordCount >= 0;
    // EFFECTS: returns days/hours/minutes to read a text, if given a word count and genre.
    //          If there are no texts of that genre, returns a string stating that there are none.
    public String calcReadingTime(Genre g, int wordCount) {
        int readingSpeed = this.calcGenreReadingSpeed(g);
        if (readingSpeed == 0) {
            return "No texts with given genre.";
        }
        int readTimeMinutes = wordCount / readingSpeed;
        int readTimeDays = readTimeMinutes / (Text.SECONDS_PER_DAY / 60);
        int readTimeRemaining = readTimeMinutes - readTimeDays * (Text.SECONDS_PER_DAY / 60);
        int readTimeHours = readTimeRemaining / (Text.SECONDS_PER_HOUR / 60);
        readTimeRemaining -= (readTimeHours * (Text.SECONDS_PER_HOUR / 60));
        return "Your reading speed is: " + readTimeDays + " days, "
                + readTimeHours + " hours, " + readTimeRemaining + " minutes";
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("texts", textsIntoJson());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return json;

    }

    // EFFECTS: adds the texts in this list of texts into a json array and returns it
    private JSONArray textsIntoJson() {
        JSONArray jsonArray = new JSONArray();
        for (Text t: texts) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // REQUIRES: 0 <= i <= (size of texts - 1)
    // EFFECTS: returns the text at index i
    public Text getTextAt(int i) {
        return texts.get(i);
    }

    public int getNumOfTexts() {
        return texts.size();
    }

    public List<Text> getTexts() {
        return texts;
    }


}
