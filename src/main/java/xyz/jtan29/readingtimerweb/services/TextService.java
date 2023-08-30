package xyz.jtan29.readingtimerweb.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.jtan29.readingtimerweb.model.*;
import xyz.jtan29.readingtimerweb.repositories.TextIdRepository;
import xyz.jtan29.readingtimerweb.repositories.TextRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private TextIdRepository textIdRepository;
    @Autowired
    private MongoOperations mongoOps;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    public Optional<Text> getTextById(int textId) {
        return textRepository.findTextByTextId(textId);
    }

    public Text createText(String title, Genre genre, int wordCount) {
        int textId = textIdRepository.findTextIdByKey("one").get().getTextId();
        int nextTextId = textId + 1;
        textIdRepository.deleteTextIdByKey("one");
        textIdRepository.insert(new TextId(nextTextId));
        Text newText = new Text(nextTextId, title, genre, wordCount);
        textRepository.insert(newText);
        mongoTemplate.update(Text.class);
        mongoTemplate.update(TextId.class);
        return newText;
    }

    public Text deleteText(int textId) {
        textRepository.deleteTextByTextId(textId);
        mongoTemplate.update(Text.class);
        return null;
    }

    public Text updateTextIsComplete(int textId, boolean isComplete) {
        Optional<Text> selectedText = textRepository.findTextByTextId(textId);
        if (selectedText.isPresent()) {
            Text existingText = selectedText.get();
            existingText.setIsComplete(isComplete);
            mongoOps.updateFirst(query(where("textId").is(textId)), update("isComplete", isComplete), Text.class);
            return existingText;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No text associated with this ID");
        }
    }

    public Text startTimer(int textId) {
        Optional<Text> selectedText = textRepository.findTextByTextId(textId);
        if (selectedText.isPresent()) {
            Text existingText = selectedText.get();
            try {
                existingText.startTimer();
            } catch (TimerAlreadyRunningException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Timer is already running.");
            }

            mongoOps.updateFirst(query(where("textId").is(textId)),
                    update("start", existingText.getStart()), Text.class);
            mongoOps.updateFirst(query(where("textId").is(textId)),
                    update("isTimerRunning", existingText.isTimerRunning()), Text.class);


            return existingText;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No text associated with this ID");
        }
    }

    public Text endTimer(int textId) {
        Optional<Text> selectedText = textRepository.findTextByTextId(textId);
        if (selectedText.isPresent()) {
            Text existingText = selectedText.get();
            try {
                existingText.endTimer();
            } catch (TimerNotRunningException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Timer is not running.");
            }
            mongoOps.updateFirst(query(where("textId").is(textId)),
                    update("end", existingText.getEnd()), Text.class);
            mongoOps.updateFirst(query(where("textId").is(textId)),
                    update("isTimerRunning", existingText.isTimerRunning()), Text.class);
            mongoOps.updateFirst(query(where("textId").is(textId)),
                    update("elapsedTime", existingText.getElapsedTime()), Text.class);
            return existingText;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No text associated with this ID");
        }

    }

}
