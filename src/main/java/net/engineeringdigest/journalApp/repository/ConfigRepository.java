package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.Config;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, ObjectId> {

}
