package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("")
    // @GetMapping("/") ----url--->  localhost:8080/journal/        (localhost:8080/journal is wrong)
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);  // Post Json Info --> Controller Receive --> Send to Service --> Write On Database
        return myEntry;
    }

    @GetMapping("/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/{myId}")
    public String deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return "The ID = "+myId+ " is deleted successfully.";
    }

    @PutMapping("/{newId}")
    public String UpdateJournalEntryById(@PathVariable ObjectId newId, @RequestBody JournalEntry newEntry) {  //   @PutMapping("/{newId}") name and the (@PathVariable ObjectId newId) should be same.
        JournalEntry oldEntry = journalEntryService.findById(newId).orElse(null);
        String msg;
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());

            msg = "The update of ID = " + newId + " was successful.";
        } else
            msg = "Journal is not found.";

        journalEntryService.saveEntry(oldEntry);
        return msg;
    }

}
