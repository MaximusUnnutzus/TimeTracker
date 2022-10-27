package ch.zhaw.timetracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.timetracker.model.TimeEntry;
import ch.zhaw.timetracker.model.User;
import ch.zhaw.timetracker.repository.TimeEntryRepository;
import ch.zhaw.timetracker.repository.UserRepository;

@Service
public class TimeEntryService {
    
    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<TimeEntry> assignEntry(String userId, String timeEntryId){
        if (userRepository.findById(userId).isPresent()){
            Optional<User> entryAddedToUser = userRepository.findById(userId);
            if (entryAddedToUser.get().get)
        }
        return null;
    }
}
