package xyz.jtan29.readingtimerweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.jtan29.readingtimerweb.model.Genre;
import xyz.jtan29.readingtimerweb.model.Text;
import xyz.jtan29.readingtimerweb.services.TextService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping
    public ResponseEntity<List<Text>> getAllTexts() {
        return new ResponseEntity<>(textService.getAllTexts(), HttpStatus.OK);
    }

    @GetMapping("/{textId}")
    public ResponseEntity<Optional<Text>> getTextById(@PathVariable int textId) {
        return new ResponseEntity<>(textService.getTextById(textId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Text> createText(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        Genre genre = Genre.valueOf(payload.get("genre"));
        int wordCount = Integer.parseInt(payload.get("wordCount"));
        return new ResponseEntity<>(textService.createText(title, genre, wordCount), HttpStatus.CREATED);
    }

    @DeleteMapping("/{textId}/delete")
    public ResponseEntity<Text> deleteText(@PathVariable int textId) {
        return new ResponseEntity<>(textService.deleteText(textId), HttpStatus.NO_CONTENT);
    }


    @PostMapping("/complete")
    public ResponseEntity<Text> updateTextIsComplete(@RequestBody Map<String, String> payload) {
        int textId = Integer.parseInt(payload.get("textId"));
        boolean complete = Boolean.parseBoolean(payload.get("isComplete"));
        return new ResponseEntity<>(textService.updateTextIsComplete(textId, complete), HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity<Text> updateTextTimerStart(@RequestBody Map<String, String> payload) {
        int textId = Integer.parseInt(payload.get("textId"));
        return new ResponseEntity<>(textService.startTimer(textId), HttpStatus.OK);
    }

    @PostMapping("/end")
    public ResponseEntity<Text> updateTextTimerEnd(@RequestBody Map<String, String> payload) {
        int textId = Integer.parseInt(payload.get("textId"));
        return new ResponseEntity<>(textService.endTimer(textId), HttpStatus.OK);
    }
}
