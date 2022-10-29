package ch.zhaw.timetracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.timetracker.enumeration.BookingState;
import ch.zhaw.timetracker.model.TimeEntry;
import ch.zhaw.timetracker.model.User;
import ch.zhaw.timetracker.repository.TimeEntryRepository;
import ch.zhaw.timetracker.repository.UserRepository;

@Service
public class TimeService {
    
    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Autowired
    UserRepository userRepository;

//Zeitbuchung erstellen + einem User zuweisen
    public Optional<User> createEntry(String timeEntryId, String userId) {
        if (timeEntryRepository.findById(timeEntryId).isPresent()) {
            //Create a variable for both types, so that both can be accessed
            Optional<User> userToGetEntry = userRepository.findById(userId);
            Optional<TimeEntry> entryToBeAssigned = timeEntryRepository.findById(timeEntryId);

            //Both the User and the Entry have to be present
            if (userToGetEntry.isPresent() && entryToBeAssigned.isPresent()) {
                User user = userToGetEntry.get();
                if (entryToBeAssigned.get().getBookingState() == BookingState.LAUFEND) {

                    //add the TimeEntryId to the List of the User
                    user.setEntries(timeEntryId);
                    userRepository.save(user);
                    //Altough the Optional of User is not being given back as an http response, the returnvalue will be checked
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }
    
}
