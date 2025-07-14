package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {
    // all business logic here

    //  @Autowired   for auto object injection in journalEntryRepository variable
    @Autowired   // Get error if you miss this Annotation
    private JournalEntryRepository journalEntryRepository;   // no need to implement, the Spring auto implement the method //
    @Autowired
    private UserService userService;

//    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);

            List<JournalEntry> journalOfUser = user.getJournalEntries();
            journalOfUser.add(journalEntry);
            user.setJournalEntries(journalOfUser);
//            Shortcut
//            user.getJournalEntries().add(journalEntry);

            // break atomicity below
//             user.setUserName(null);   to solve, we need to use  @Transactional to make whole function in one entity
            userService.saveUser(user);
        } catch (Exception e) {
            log.error("Error",e);
            throw new RuntimeException("An error occurred while saving the entries",e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myID) {  // Optional<T> means, it can return an empty box or an object
        return journalEntryRepository.findById(myID);
    }

    @Transactional
    public void  deleteById(ObjectId myId, String userName) {
        try {
            // remove journal reference from user
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));

            if (removed){
                userService.saveUser(user);
                // remove journal reference
                journalEntryRepository.deleteById(myId);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.");
        }
    }

}
