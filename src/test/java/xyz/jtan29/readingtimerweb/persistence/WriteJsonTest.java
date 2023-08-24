package xyz.jtan29.readingtimerweb.persistence;

import xyz.jtan29.readingtimerweb.model.FictionGenre;
import xyz.jtan29.readingtimerweb.model.ListOfText;
import xyz.jtan29.readingtimerweb.model.NonFictionGenre;
import xyz.jtan29.readingtimerweb.model.Text;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriteJsonTest {

    @Test
    public void testInvalidFile() {
        WriteJson writer = new WriteJson(".data/test\0adfka");
        try {
            writer.start();
            fail("Should fail, invalid file given");
        } catch (FileNotFoundException e) {
            // fine
        }
    }

    @Test
    public void testEmptyListOfTexts() {
        ListOfText texts = new ListOfText();
        WriteJson writer = new WriteJson("./data/testEmptyListOfTexts.json");
        try {
            writer.start();
            writer.writeFile(texts);
            writer.close();
            ReadJson reader = new ReadJson("./data/testEmptyListOfTexts.json");
            texts = reader.read();
            assertEquals(0, texts.getNumOfTexts());
        } catch (IOException e) {
            fail("should pass");
        }
    }

    @Test
    public void testListOfTextsWithTexts() {
        ListOfText texts = new ListOfText();
        Text t1 = new Text(1000, "Test", FictionGenre.YA);
        Text t2 = new Text(2000, "Another Test", NonFictionGenre.MEMOIR);
        Text t3 = new Text(3000, "I should have made less genres", FictionGenre.SHORT_STORY);
        t2.setIsComplete(true);
        t2.addTime(10000);
        texts.addText(t1);
        texts.addText(t2);
        texts.addText(t3);
        WriteJson writer = new WriteJson("./data/testListOfTextsWithTexts.json");
        try {
            writer.start();
            writer.writeFile(texts);
            writer.close();
            ReadJson reader = new ReadJson("./data/testListOfTextsWithTexts.json");
            texts = reader.read();
            assertEquals(3, texts.getNumOfTexts());
            Text tt1 = texts.getTextAt(0);
            Text tt2 = texts.getTextAt(1);
            Text tt3 = texts.getTextAt(2);
            assertEquals("Test", tt1.getTitle());
            assertEquals("Another Test", tt2.getTitle());
            assertEquals(1000, tt1.getWordCount());
            assertEquals(2000, tt2.getWordCount());
            assertEquals(3000, tt3.getWordCount());
            assertEquals(FictionGenre.YA, tt1.getGenre());
            assertEquals(NonFictionGenre.MEMOIR, tt2.getGenre());
            assertEquals(FictionGenre.SHORT_STORY, tt3.getGenre());
            assertFalse(tt1.getIsComplete());
            assertTrue(tt2.getIsComplete());
            assertFalse(tt3.getIsComplete());
            assertEquals(0, tt1.getElapsedTime());
            assertEquals(10000, tt2.getElapsedTime());
            assertEquals(0, tt3.getElapsedTime());
        } catch (IOException e) {
            fail("should pass");
        }
    }

    @Test
    public void testListOfTextsWithTextsOtherGenres() {
        Text t1 = new Text(100, "A", FictionGenre.GENERAL);
        Text t2 = new Text(100, "B", FictionGenre.CHILDREN);
        Text t3 = new Text(100, "C", FictionGenre.GRAPHIC);
        Text t4 = new Text(100, "D", FictionGenre.OTHER);
        Text t5 = new Text(100, "E", NonFictionGenre.ARTICLE);
        Text t6 = new Text(100, "F", NonFictionGenre.SELF_HELP);
        Text t7 = new Text(100, "G", NonFictionGenre.TEXTBOOK);
        Text t8 = new Text(100, "H", NonFictionGenre.NF_OTHER);
        ListOfText texts = new ListOfText();
        texts.addText(t1);
        texts.addText(t2);
        texts.addText(t3);
        texts.addText(t4);
        texts.addText(t5);
        texts.addText(t6);
        texts.addText(t7);
        texts.addText(t8);
        WriteJson writer = new WriteJson("./data/testListOfTextsWithTextsOtherGenres.json");
        try {
            writer.start();
            writer.writeFile(texts);
            writer.close();
            ReadJson reader = new ReadJson("./data/testListOfTextsWithTextsOtherGenres.json");
            texts = reader.read();
            Text tt1 = texts.getTextAt(0);
            Text tt2 = texts.getTextAt(1);
            Text tt3 = texts.getTextAt(2);
            Text tt4 = texts.getTextAt(3);
            Text tt5 = texts.getTextAt(4);
            Text tt6 = texts.getTextAt(5);
            Text tt7 = texts.getTextAt(6);
            Text tt8 = texts.getTextAt(7);
            assertEquals(FictionGenre.GENERAL, tt1.getGenre());
            assertEquals(FictionGenre.CHILDREN, tt2.getGenre());
            assertEquals(FictionGenre.GRAPHIC, tt3.getGenre());
            assertEquals(FictionGenre.OTHER, tt4.getGenre());
            assertEquals(NonFictionGenre.ARTICLE, tt5.getGenre());
            assertEquals(NonFictionGenre.SELF_HELP, tt6.getGenre());
            assertEquals(NonFictionGenre.TEXTBOOK, tt7.getGenre());
            assertEquals(NonFictionGenre.NF_OTHER, tt8.getGenre());
        } catch (IOException e) {
            fail("should not throw exceptions");
        }
    }
}
