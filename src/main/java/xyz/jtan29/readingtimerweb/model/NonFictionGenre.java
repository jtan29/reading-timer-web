package xyz.jtan29.readingtimerweb.model;

// Representation of available non-fictional genres for a text
public enum NonFictionGenre implements Genre {
    ARTICLE("Non-fiction article"),
    TEXTBOOK("Textbook or other educational book"),
    MEMOIR("Memoir, biography, or autobiography"),
    SELF_HELP("Self-help book"),
    NF_OTHER("Any other non-fiction material");

    private final String genreDescription;

    // MODIFIES: this
    // EFFECTS: creates new nonfiction genre
    NonFictionGenre(String s) {
        genreDescription = s;
    }

    public String getGenreDescription() {
        return genreDescription;
    }
}
