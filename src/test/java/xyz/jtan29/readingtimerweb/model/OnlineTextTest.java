package xyz.jtan29.readingtimerweb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OnlineTextTest {
    private OnlineText ot1;
    private OnlineText ot2;

    @BeforeEach
    public void setUp() {
        ot1 = new OnlineText(1000, "Test", NonFictionGenre.MEMOIR, "https://canvas.ubc.ca");
        ot2 = new OnlineText(2000, "Another Test", FictionGenre.GENERAL,"https://www.youtube.com/");
    }

    @Test
    public void testConstructor() {
        assertEquals(1000, ot1.getWordCount());
        assertEquals("Test", ot1.getTitle());
        assertEquals(NonFictionGenre.MEMOIR, ot1.getGenre());
        assertEquals("https://canvas.ubc.ca", ot1.getLink());
    }

    @Test
    public void testSetLink() {
        ot1.setLink("https://www.test.com");
        assertEquals("https://www.test.com", ot1.getLink());
    }
}
