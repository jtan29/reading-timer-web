package xyz.jtan29.readingtimerweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jtan29.readingtimerweb.model.Text;
import xyz.jtan29.readingtimerweb.services.TextService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping
    public ResponseEntity<List<Text>> getAllTexts() {
        return new ResponseEntity<List<Text>>(textService.getAllTexts(), HttpStatus.OK);
    }
}
