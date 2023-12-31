package xyz.jtan29.readingtimerweb.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jtan29.readingtimerweb.model.Text;

import java.util.Optional;

// Representation of TextIds stored in database
@Repository
public interface TextRepository extends MongoRepository<Text, ObjectId> {

    Optional<Text> findTextByTextId(int textId);

    Optional<Text> deleteTextByTextId(int textId);



}
