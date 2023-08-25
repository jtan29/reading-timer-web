package xyz.jtan29.readingtimerweb.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jtan29.readingtimerweb.model.Text;
import xyz.jtan29.readingtimerweb.repositories.TextRepository;

import java.util.List;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }
}
