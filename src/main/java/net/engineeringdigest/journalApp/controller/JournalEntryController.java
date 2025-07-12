package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping()   //  get{userName} and get{myId} both are same
    // @GetMapping("/") ----url--->  localhost:8080/journal/        (localhost:8080/journal is wrong)
//    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
    public ResponseEntity<?> getAllJournalEntriesOfUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // already authorized.
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        // Check if user exists first
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // already authorized.
        String userName = authentication.getName();

        try {
            journalEntryService.saveEntry(myEntry, userName);  // Post Json Info --> Controller Receive --> Send to Service --> Write On Database
            return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);
        // Find the all the journal entries of this user
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

            if (journalEntry.isPresent()) {
                return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>("The ID = " + myId + " is deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<String> UpdateJournalEntryById(@PathVariable ObjectId myId,
                                                         @RequestBody JournalEntry newEntry) {  //   @PutMapping("/{newId}") name and the (@PathVariable ObjectId newId) should be same.

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        JournalEntry oldEntry = journalEntryService.findById(myId).get();
        String msg;
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());

            journalEntryService.saveEntry(oldEntry, userName);
            msg = "The update of ID = " + myId + " was successful.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg = "Journal is not found.";
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

}
