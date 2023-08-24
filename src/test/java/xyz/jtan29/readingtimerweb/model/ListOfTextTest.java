package xyz.jtan29.readingtimerweb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfTextTest {
    private Text tt1;
    private Text tt2;
    private Text tt3;
    private ListOfText lot1;
    private ListOfText lot2;

    @BeforeEach
    public void setUp() {
        tt1 = new Text(100, "Test", FictionGenre.GENERAL);
        tt2 = new Text(200, "Another Test", NonFictionGenre.ARTICLE);
        tt3 = new Text(300, "Test Three", FictionGenre.GENERAL);

        lot1 = new ListOfText();
        lot2 = new ListOfText();
    }
    @Test
    public void testConstructor() {
        assertEquals(0, lot1.getNumOfTexts());
        List<Text> testList = new ArrayList<>();
        assertEquals(testList, lot1.getTexts());
        assertEquals(0, lot2.getNumOfTexts());
        assertEquals(testList, lot2.getTexts());
    }

    @Test
    public void testAddOneText() {
        lot1.addText(tt1);
        assertEquals(1, lot1.getNumOfTexts());
        assertEquals(tt1, lot1.getTextAt(0));
    }

    @Test
    public void testAddTwoTexts() {
        lot1.addText(tt1);
        lot1.addText(tt2);
        assertEquals(2, lot1.getNumOfTexts());
        assertEquals(tt1, lot1.getTextAt(0));
        assertEquals(tt2, lot1.getTextAt(1));
    }

    @Test
    public void testRemoveOneText() {
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(2, lot1.getNumOfTexts());
        lot1.removeText(tt2);
        assertEquals(1, lot1.getNumOfTexts());
        assertEquals(tt3, lot1.getTextAt(0));
    }

    @Test
    public void testRemoveTwoTextsListEmpty() {
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(2, lot1.getNumOfTexts());
        lot1.removeText(tt2);
        lot1.removeText(tt3);
        assertEquals(0, lot1.getNumOfTexts());
    }

    @Test
    public void testRemoveTwoTextsOneLeft() {
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(3, lot1.getNumOfTexts());
        lot1.removeText(tt2);
        lot1.removeText(tt1);
        assertEquals(1, lot1.getNumOfTexts());
        assertEquals(tt3, lot1.getTextAt(0));
    }

    @Test
    public void testCalcTotalAverageReadingSpeedOneText() {
        tt1.addTime(1000);
        tt1.setIsComplete(true);
        lot1.addText(tt1);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(6, result);
    }

    @Test
    public void testCalcTotalAverageReadingSpeedTwoTextsSameGenre() {
        tt1.addTime(3600);
        tt1.setIsComplete(true);
        tt3.addTime(60 * 30);
        tt3.setIsComplete(true);
        lot1.addText(tt1);
        lot1.addText(tt3);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(4, result);
    }

    @Test
    public void testCalcTotalAverageReadingSpeedThreeTextsOneDifferentGenre() {
        tt1.addTime(3600);
        tt2.addTime(60 * 20);
        tt3.addTime(60 * 40);
        tt1.setIsComplete(true);
        tt2.setIsComplete(true);
        tt3.setIsComplete(true);
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(4, result);
    }

    @Test
    public void testCalcTotalAverageReadingSpeedOneNotComplete() {
        tt1.addTime(3600);
        tt2.addTime(60 * 20);
        tt3.addTime(60 * 40);
        tt1.setIsComplete(true);
        tt2.setIsComplete(true);
        tt3.setIsComplete(false);
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(1, result);
    }

    @Test
    public void testCalcTotalAverageReadingSpeedLessThan1Min() {
        tt1.addTime(1);
        tt2.addTime(1);
        tt3.addTime(1);
        tt1.setIsComplete(true);
        tt2.setIsComplete(true);
        tt3.setIsComplete(false);
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(100, result);
    }

    @Test
    public void testCalcReadTime() {
        tt1.addTime(3600);
        tt1.setIsComplete(true);
        tt3.addTime(60 * 30);
        tt3.setIsComplete(true);
        lot1.addText(tt1);
        lot1.addText(tt3);
        String actualResult = lot1.calcReadingTime(FictionGenre.GENERAL, 1000);
        String expectedResult = "Your reading speed is: " + "0" + " days, " + "4" + " hours, " + 10 + " minutes";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testCalcReadTimeNone() {
        tt1.addTime(3600);
        tt1.setIsComplete(true);
        tt3.addTime(60 * 30);
        tt3.setIsComplete(true);
        lot1.addText(tt1);
        lot1.addText(tt3);
        String actualResult = lot1.calcReadingTime(FictionGenre.SHORT_STORY, 1000);
        String expectedResult = "No texts with given genre.";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetTextAt() {
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(tt3, lot1.getTextAt(2));
    }
}
