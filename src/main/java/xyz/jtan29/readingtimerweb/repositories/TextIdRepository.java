package xyz.jtan29.readingtimerweb.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jtan29.readingtimerweb.model.Text;
import xyz.jtan29.readingtimerweb.model.TextId;

import java.util.Optional;

@Repository
public interface TextIdRepository extends MongoRepository<TextId, ObjectId>{
    Optional<TextId> findTextIdByKey(String key);
    Optional<TextId> deleteTextIdByKey(String key);
}