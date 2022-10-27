package ch.zhaw.timetracker.model;


import ch.zhaw.timetracker.enumeration.BookingState;
import ch.zhaw.timetracker.enumeration.BookingType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TimeEntryUpdateDTO {

	private BookingType bookingType;
    private BookingState bookingState;
}
