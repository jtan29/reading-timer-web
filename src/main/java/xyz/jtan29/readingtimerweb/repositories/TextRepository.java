package xyz.jtan29.readingtimerweb.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jtan29.readingtimerweb.model.Text;

@Repository
public interface TextRepository extends MongoRepository<Text, ObjectId> {
}
