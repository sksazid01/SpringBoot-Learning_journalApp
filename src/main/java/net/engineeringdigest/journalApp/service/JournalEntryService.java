package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {
    // all business logic here

    //  @Autowired   for auto object injection in journalEntryRepository variable
    @Autowired   // Get error if you miss this Annotation
    private JournalEntryRepository journalEntryRepository;   // no need to implement, the Spring auto implement the method //

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional <JournalEntry> findById(ObjectId myID){  // Optional<T> means, it can return an empty box or an object
        return journalEntryRepository.findById(myID);
    }
    public void deleteById(ObjectId myId){
         journalEntryRepository.deleteById(myId);
    }
}
