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
@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping("/{textId}/complete")
    public ResponseEntity<Text> updateTextIsComplete(@PathVariable int textId) {
        return new ResponseEntity<>(textService.updateTextIsComplete(textId, true), HttpStatus.OK);
    }

    @PutMapping("/{textId}/incomplete")
    public ResponseEntity<Text> updateTextNotComplete(@PathVariable int textId) {
        return new ResponseEntity<>(textService.updateTextIsComplete(textId, false), HttpStatus.OK);
    }

    @PutMapping("/{textId}/start")
    public ResponseEntity<Text> updateTextTimerStart(@PathVariable int textId) {
        return new ResponseEntity<>(textService.startTimer(textId), HttpStatus.OK);
    }

    @PutMapping("/{textId}/end")
    public ResponseEntity<Text> updateTextTimerEnd(@PathVariable int textId) {
        return new ResponseEntity<>(textService.endTimer(textId), HttpStatus.OK);
    }
}
