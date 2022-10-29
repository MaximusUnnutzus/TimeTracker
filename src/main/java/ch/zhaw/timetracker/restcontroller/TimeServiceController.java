package ch.zhaw.timetracker.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.timetracker.model.TimeEntry;
import ch.zhaw.timetracker.model.TimeEntryCreateDTO;
import ch.zhaw.timetracker.model.User;
import ch.zhaw.timetracker.repository.TimeEntryRepository;
import ch.zhaw.timetracker.repository.UserRepository;
import ch.zhaw.timetracker.service.TimeService;

@RestController
@RequestMapping("api/service")
public class TimeServiceController {
    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeService timeService;

    // Zeitbuchung erstellen + einem User zuweisen
    @PostMapping("/create")
    public ResponseEntity<TimeEntry> createEntry(@RequestBody TimeEntryCreateDTO tDTO) {
        try {
            // create a new Object
            TimeEntry tDAO = new TimeEntry(tDTO.getStartPoint(), tDTO.getComment(), tDTO.getUserId(),
                    tDTO.getBookingType());

            // Save that object in the DB (muss das im TimeService geschehen?)
            TimeEntry newTimeEntry = timeEntryRepository.save(tDAO);

            // assign the TimeEntryId to the User
            timeService.createEntry(newTimeEntry.getTimeEntryId(), tDTO.getUserId());

            return new ResponseEntity<>(newTimeEntry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable String userId) {
        // Create a new Object
        Optional<User> optUser = userRepository.findById(userId);
        // If the Object is present
        if (optUser.isPresent()) {
            // Call the repository Method deleteById to delete the User
            userRepository.deleteById(userId);
            // After that, delete the entries the user made and save the length of the array
            // to a list
            int numberOfDeletedEntries = (timeEntryRepository.deleteByUserId(userId)).size();
            return new ResponseEntity<>(numberOfDeletedEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
