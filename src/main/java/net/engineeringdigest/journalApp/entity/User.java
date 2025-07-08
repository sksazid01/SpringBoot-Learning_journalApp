package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "journal_entries")  // It mapped with row value of database
@Data  // Project Lombok, That auto generate Getter, Setter, ToString etc. using @Data annotation.
public class User {
    @Id  // Make id as a primary key
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull   // Lombok throw nullPointerException if the field is null.
    private String password;

    @DBRef // Patent-Child Relation created.
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
