package xyz.jtan29.readingtimerweb.model;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

// A representation of a TextId, used to uniquely identify every Text
@Document(collection = "text-id")
@Data
public class TextId {
    @Getter
    private int textId;
    private String key;

    // REQUIRES: textId >= 0
    // EFFECTS: creates a new TextId with the given textId and a key of "one"
    public TextId(int textId) {
        this.textId = textId;
        this.key = "one";
    }

}
