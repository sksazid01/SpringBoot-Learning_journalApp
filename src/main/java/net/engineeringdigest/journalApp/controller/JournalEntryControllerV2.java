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

    @GetMapping("")         // @GetMapping("/") ----url--->  localhost:8080/journal/        (localhost:8080/journal is wrong)
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public Boolean createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);  // Post Json Info --> Controller Receive --> Send to Service --> Write On Database
        return true;
    }

//    @GetMapping("/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
//        return journalEntryService.getJournalEntryById(myId);
//    }

}
