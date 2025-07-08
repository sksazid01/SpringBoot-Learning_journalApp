package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Component
public class JournalEntryService {
    // all business logic here

    //  @Autowired   for auto object injection in journalEntryRepository variable
    @Autowired   // Get error if you miss this Annotation
    private JournalEntryRepository journalEntryRepository;   // no need to implement, the Spring auto implement the method //
    @Autowired
    private UserService userService;


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

            userService.saveEntry(user);

        } catch (Exception e) {
            log.println("Exception " + e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);

        } catch (Exception e) {
            log.println("Exception " + e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myID) {  // Optional<T> means, it can return an empty box or an object
        return journalEntryRepository.findById(myID);
    }

    public void deleteById(ObjectId myId, String userName) {
       // remove journal reference from user
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);

        // remove journal reference
        journalEntryRepository.deleteById(myId);
    }
}
