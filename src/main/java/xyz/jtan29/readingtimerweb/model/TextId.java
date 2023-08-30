package xyz.jtan29.readingtimerweb.model;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "text-id")
@Data
public class TextId {
    @Getter
    private int textId;
    private String key;

    public TextId(int textId) {
        this.textId = textId;
        this.key = "one";
    }

}
