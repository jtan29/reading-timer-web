package xyz.jtan29.readingtimerweb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GenreTest {
    @Test
    public void testConstructor() {
        assertEquals("Non-fiction article", Genre.ARTICLE.getGenreDescription());
        assertEquals("Textbook or other educational book", Genre.TEXTBOOK.getGenreDescription());
        assertEquals("Memoir, biography, or autobiography", Genre.MEMOIR.getGenreDescription());
        assertEquals("Self-help book", Genre.SELF_HELP.getGenreDescription());
        assertEquals("Any other non-fiction material", Genre.NF_OTHER.getGenreDescription());
        assertEquals("Fiction book", Genre.GENERAL.getGenreDescription());
        assertEquals("Young adult book", Genre.YA.getGenreDescription());
        assertEquals("Graphic novel", Genre.GRAPHIC.getGenreDescription());
        assertEquals("Children's book", Genre.CHILDREN.getGenreDescription());
        assertEquals("Short story", Genre.SHORT_STORY.getGenreDescription());
        assertEquals("Any other fictional material", Genre.F_OTHER.getGenreDescription());
    }
}

