package xyz.jtan29.readingtimerweb.model;

// Representation of available fictional genres for a text
public enum FictionGenre implements Genre {
    GENERAL("Fiction book"),
    YA("Young adult book"),
    GRAPHIC("Graphic novel"),
    CHILDREN("Children's book"),
    SHORT_STORY("Short story"),
    OTHER("Any other fictional material");

    private final String genreDescription;

    // MODIFIES: this
    // EFFECTS: creates new fiction genre
    FictionGenre(String s) {
        genreDescription = s;
    }

    public String getGenreDescription() {
        return genreDescription;
    }
}
