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

// The REST API controller for Texts, with basic CRUD functionality
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TextController {

    @Autowired
    private TextService textService;

    // EFFECTS: returns all the Texts in the database
    @GetMapping
    public ResponseEntity<List<Text>> getAllTexts() {
        return new ResponseEntity<>(textService.getAllTexts(), HttpStatus.OK);
    }

    // EFFECTS: gets a singular Text, if it exists, by its TextId
    @GetMapping("/{textId}")
    public ResponseEntity<Optional<Text>> getTextById(@PathVariable int textId) {
        return new ResponseEntity<>(textService.getTextById(textId), HttpStatus.OK);

    }

    // EFFECTS: adds a new Text with the specified title, genre, and word count to the database
    @PostMapping
    public ResponseEntity<Text> createText(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        Genre genre = Genre.valueOf(payload.get("genre"));
        int wordCount = Integer.parseInt(payload.get("wordCount"));
        return new ResponseEntity<>(textService.createText(title, genre, wordCount), HttpStatus.CREATED);
    }

    // EFFECTS: deletes specified Text by its TextId
    @DeleteMapping("/{textId}/delete")
    public ResponseEntity<Text> deleteText(@PathVariable int textId) {
        return new ResponseEntity<>(textService.deleteText(textId), HttpStatus.NO_CONTENT);
    }

    // EFFECTS: updates the completion status of a Text with matching TextId to complete
    @PutMapping("/{textId}/complete")
    public ResponseEntity<Text> updateTextIsComplete(@PathVariable int textId) {
        return new ResponseEntity<>(textService.updateTextIsComplete(textId, true), HttpStatus.OK);
    }

    // EFFECTS: updates the completion status of a Text with matching TextId to not complete
    @PutMapping("/{textId}/incomplete")
    public ResponseEntity<Text> updateTextNotComplete(@PathVariable int textId) {
        return new ResponseEntity<>(textService.updateTextIsComplete(textId, false), HttpStatus.OK);
    }

    // EFFECTS: starts timer of Text with corresponding TextId
    @PutMapping("/{textId}/start")
    public ResponseEntity<Text> updateTextTimerStart(@PathVariable int textId) {
        return new ResponseEntity<>(textService.startTimer(textId), HttpStatus.OK);
    }

    // EFFECTS: ends timer of Text with corresponding TextId
    @PutMapping("/{textId}/end")
    public ResponseEntity<Text> updateTextTimerEnd(@PathVariable int textId) {
        return new ResponseEntity<>(textService.endTimer(textId), HttpStatus.OK);
    }
}
