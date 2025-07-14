package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    // all business logic here

    //  @Autowired   for auto object injection in journalEntryRepository variable
    @Autowired   // Get error if you miss this Annotation
    private UserRepository userRepository;   // no need to implement, the Spring auto implement the method //
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }catch (Exception e){
            logger.error("Error occurred for {}: ",user.getUserName(),e);  // output: Error occurred for sazid: e
//            logger.warn("Error: ",e);
//            logger.info("Error: ",e);
//            logger.debug("Error: ",e);
//            logger.trace("Error: ",e);
        }

    }

    public void saveAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
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
