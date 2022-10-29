package ch.zhaw.timetracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.timetracker.model.TimeEntry;


//Wieso bringt es mir hier etwas, wenn ich List<String> zurückgebe
public interface TimeEntryRepository extends MongoRepository<TimeEntry, String> {
    
    List<TimeEntry> deleteByUserId(String userId);
}
