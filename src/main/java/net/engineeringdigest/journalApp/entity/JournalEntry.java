package net.engineeringdigest.journalApp.entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")  // It mapped with row value of database
@Data  // Project Lombok, That auto generate Getter, Setter, ToString etc. using @Data annotation.
public class JournalEntry {

    @Id  // Make id as a primary key
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
