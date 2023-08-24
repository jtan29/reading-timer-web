package xyz.jtan29.readingtimerweb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FictionGenreTest {
    @Test
    public void testConstructor() {
        assertEquals("Fiction book", FictionGenre.GENERAL.getGenreDescription());
        assertEquals("Young adult book", FictionGenre.YA.getGenreDescription());
        assertEquals("Graphic novel", FictionGenre.GRAPHIC.getGenreDescription());
        assertEquals("Children's book", FictionGenre.CHILDREN.getGenreDescription());
        assertEquals("Short story", FictionGenre.SHORT_STORY.getGenreDescription());
        assertEquals("Any other fictional material", FictionGenre.OTHER.getGenreDescription());
    }
}
