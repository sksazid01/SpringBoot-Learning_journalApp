package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class UserService {
    // all business logic here

    //  @Autowired   for auto object injection in journalEntryRepository variable
    @Autowired   // Get error if you miss this Annotation
    private UserRepository userRepository;   // no need to implement, the Spring auto implement the method //
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveNewUser(User user){
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(Arrays.asList("USER"));
           userRepository.save(user);
    }

    public void saveUser(User user){
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
    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
