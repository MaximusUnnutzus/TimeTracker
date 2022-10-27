package ch.zhaw.timetracker.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ch.zhaw.timetracker.entities.TimeEntry;
import ch.zhaw.timetracker.entities.TimeEntryCreateDTO;
import ch.zhaw.timetracker.repository.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TimeEntryRestController {
	@Autowired
	TimeEntryRepository timeEntryRepository;

	@PostMapping("/Time/create")
	public ResponseEntity<TimeEntry> createEntry(
			@RequestBody TimeEntryCreateDTO uDTO) {
		TimeEntry uDAO = new TimeEntry(uDTO.getStartPoint(),uDTO.getComment());
		TimeEntry u = timeEntryRepository.save(uDAO);
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}

	@GetMapping("/Time/create")
	public ResponseEntity<List<TimeEntry>> getAllEntries() {
		List<TimeEntry> allEntries = timeEntryRepository.findAll();
		return new ResponseEntity<>(allEntries, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TimeEntry> getEntryById(@PathVariable String id) {
		Optional<TimeEntry> oneEntry = timeEntryRepository.findById(id);
		if (oneEntry.isPresent()) {
			return new ResponseEntity<>(oneEntry.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}