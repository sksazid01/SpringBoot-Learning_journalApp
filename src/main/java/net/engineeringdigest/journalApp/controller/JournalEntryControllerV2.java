package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("")
    // @GetMapping("/") ----url--->  localhost:8080/journal/        (localhost:8080/journal is wrong)
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            journalEntryService.saveEntry(myEntry);  // Post Json Info --> Controller Receive --> Send to Service --> Write On Database
            return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

        if(journalEntry.isPresent()){
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        }else{
           return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>("The ID = "+myId+ " is deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("/{newId}")
    public ResponseEntity<String> UpdateJournalEntryById(@PathVariable ObjectId newId, @RequestBody JournalEntry newEntry) {  //   @PutMapping("/{newId}") name and the (@PathVariable ObjectId newId) should be same.
        JournalEntry oldEntry = journalEntryService.findById(newId).orElse(null);
        String msg;
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());

            journalEntryService.saveEntry(oldEntry);
            msg = "The update of ID = " + newId + " was successful.";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        } else {
            msg = "Journal is not found.";
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }
    }

}
