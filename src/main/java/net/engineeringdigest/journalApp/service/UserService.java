package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {
    // all business logic here

    //  @Autowired   for auto object injection in journalEntryRepository variable
    @Autowired   // Get error if you miss this Annotation
    private UserRepository userRepository;   // no need to implement, the Spring auto implement the method //

    public void saveEntry(User user){
           userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public Optional <User> findById(ObjectId myID){  // Optional<T> means, it can return an empty box or an object
        return userRepository.findById(myID);
    }
    public void deleteById(ObjectId myId){
        userRepository.deleteById(myId);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
