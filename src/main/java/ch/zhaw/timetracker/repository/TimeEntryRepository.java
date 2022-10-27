package ch.zhaw.timetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.timetracker.model.TimeEntry;

public interface TimeEntryRepository extends MongoRepository<TimeEntry, String> {

}
