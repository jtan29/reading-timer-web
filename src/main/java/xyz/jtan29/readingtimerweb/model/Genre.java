package xyz.jtan29.readingtimerweb.model;

// Representation of available non-fictional genres for a text
public enum Genre {
    GENERAL("Fiction book"),
    YA("Young adult book"),
    GRAPHIC("Graphic novel"),
    CHILDREN("Children's book"),
    SHORT_STORY("Short story"),
    F_OTHER("Any other fictional material"),
    ARTICLE("Non-fiction article"),
    TEXTBOOK("Textbook or other educational book"),
    MEMOIR("Memoir, biography, or autobiography"),
    SELF_HELP("Self-help book"),
    NF_OTHER("Any other non-fiction material");

    private final String genreDescription;

    // MODIFIES: this
    // EFFECTS: creates new fiction genre
    Genre(String s) {
        genreDescription = s;
    }

    public String getGenreDescription() {
        return genreDescription;
    }
}
