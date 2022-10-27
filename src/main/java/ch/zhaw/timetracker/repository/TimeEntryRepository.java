package ch.zhaw.timetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ch.zhaw.timetracker.entities.TimeEntry;

public interface TimeEntryRepository extends MongoRepository<TimeEntry, String> {

}
