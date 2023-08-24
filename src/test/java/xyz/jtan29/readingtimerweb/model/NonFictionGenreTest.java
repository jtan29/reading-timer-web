package xyz.jtan29.readingtimerweb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NonFictionGenreTest {
    @Test
    public void testConstructor() {
        assertEquals("Non-fiction article", NonFictionGenre.ARTICLE.getGenreDescription());
        assertEquals("Textbook or other educational book", NonFictionGenre.TEXTBOOK.getGenreDescription());
        assertEquals("Memoir, biography, or autobiography", NonFictionGenre.MEMOIR.getGenreDescription());
        assertEquals("Self-help book", NonFictionGenre.SELF_HELP.getGenreDescription());
        assertEquals("Any other non-fiction material", NonFictionGenre.NF_OTHER.getGenreDescription());
    }
}
