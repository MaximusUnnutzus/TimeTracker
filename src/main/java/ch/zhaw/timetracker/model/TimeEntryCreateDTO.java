package ch.zhaw.timetracker.model;
import java.time.LocalDateTime;

import ch.zhaw.timetracker.enumeration.BookingType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TimeEntryCreateDTO {
	private LocalDateTime startPoint;
	private String comment;
	private BookingType bookingType;	
}
