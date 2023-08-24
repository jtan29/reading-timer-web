package xyz.jtan29.readingtimerweb.model;

// Representation of a text in an online format
public class OnlineText extends Text {
    private String link;

    // MODIFIES: this
    // EFFECTS: creates a new OnlineText
    public OnlineText(int wordCount, String title, Genre g, String link) {
        super(wordCount, title, g);
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link; // stub
    }

}
