package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntry = new HashMap<>();

//    @GetMapping
//    public List<JournalEntry> getAll(){
//            return new ArrayList<>(journalEntry.values());
//    }

    @PostMapping
    public Boolean createEntry(@RequestBody JournalEntry myEntry){
            journalEntry.put(myEntry.getId(),myEntry);
            return true;
    }

    @GetMapping("{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
            return journalEntry.get(myId);
    }

}
