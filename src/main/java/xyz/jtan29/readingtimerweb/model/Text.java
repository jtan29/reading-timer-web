package xyz.jtan29.readingtimerweb.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.Instant;

// Representation of a text, with a genre, timer, tracked elapsed time (in seconds), a word count, and a title
// Note: implementation of the timer made with the help of guide from https://www.baeldung.com/java-measure-elapsed-time
@Document(collection = "texts")
@Data
public class Text {

    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int MILLISECONDS_PER_SECOND = 1000;

    @Getter
    private int textId;
    @Getter
    private String title;
    @Getter
    private int wordCount;
    @Getter
    private Genre genre;
    @Getter
    private boolean isComplete;
    @Getter
    private long elapsedTime;
    @Getter
    private boolean isTimerRunning;
    @Getter
    private String timeStatement;

    private Instant start;
    private Instant end;


    // REQUIRES: wordCount >= 0
    // EFFECTS: sets the text's word count to wordCount, the text's title is
    //          set to the given title, and the text's genre is set to the given genre.
    //          Begins with zero seconds elapsed and marks the text as not having a running timer
    //          and incomplete.

    public Text(int textId, String title, Genre genre, int wordCount) {
        this.textId = textId;
        this.wordCount = wordCount;
        this.title = title;
        this.genre = genre;
        elapsedTime = 0;
        isTimerRunning = false;
        isComplete = false;
        this.start = Instant.now();
        this.end = Instant.now();
        timeStatement = this.calcTimeStatement();

    }

    // REQUIRES: timer is not running
    // MODIFIES: this
    // EFFECTS: begins the timer
    public void startTimer() throws TimerAlreadyRunningException {
        if (isTimerRunning) {
            throw new TimerAlreadyRunningException();
        }
        start = Instant.now();
        isTimerRunning = true;
    }

    // REQUIRES: timer is running
    // MODIFIES: this
    // EFFECTS: stops the timer and adds elapsed time to total (in seconds)
    public void endTimer() throws TimerNotRunningException {
        if (!isTimerRunning) {
            throw new TimerNotRunningException();
        }
        end = Instant.now();
        isTimerRunning = false;
        Duration duration = Duration.between(start, end);
        elapsedTime += (duration.toMillis() / MILLISECONDS_PER_SECOND);
        timeStatement = this.calcTimeStatement();
    }

    // REQUIRES: given time interval is not negative
    // MODIFIES: this
    // EFFECTS: increases the elapsed time by given time (seconds)
    public void addTime(long time) {
        elapsedTime += time;
    }


    // MODIFIES: this
    // EFFECTS: decreases the elapsed time by given time (seconds) if given time is less than elapsed time,
    //          otherwise does nothing

    public void removeTime(long time) {
        if (time <= elapsedTime) {
            elapsedTime = elapsedTime - time;
        }
    }


    // EFFECTS: calculates a day/hours/minutes/seconds statement for the elapsed time
    public String calcTimeStatement() {
        long elapsedDays = elapsedTime / SECONDS_PER_DAY;
        long remainingTime = elapsedTime - (elapsedDays * SECONDS_PER_DAY);
        long elapsedHours = remainingTime / SECONDS_PER_HOUR;
        remainingTime = remainingTime - (elapsedHours * SECONDS_PER_HOUR);
        long elapsedMinutes = remainingTime / SECONDS_PER_MINUTE;
        remainingTime = remainingTime - (elapsedMinutes * SECONDS_PER_MINUTE);
        return "\n" + elapsedDays + " " + "day(s), " + elapsedHours + " hour(s), "
                + elapsedMinutes + " minute(s), "
                + remainingTime + " second(s).";
    }

    // REQUIRES: the text is marked complete
    // EFFECTS: calculates the average reading speed for the text
    public long calcReadingSpeed() {
        long readingSpeed;
        if ((elapsedTime / SECONDS_PER_MINUTE) == 0) {
            readingSpeed = wordCount;
        } else {
            readingSpeed = wordCount / (elapsedTime / SECONDS_PER_MINUTE);
        }
        return readingSpeed;
    }



    public void setIsComplete(boolean b) {
        this.isComplete = b;
    }

    // MODIFIES: this
    // EFFECTS: changes the text's title
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    // REQUIRES: newWordCount >= 0
    // MODIFIES: this
    // EFFECTS: changes the text's word count
    public void setWordCount(int newWordCount) {
        this.wordCount = newWordCount;
    }

    public void setGenre(Genre g) {
        this.genre = g;
    }


}
