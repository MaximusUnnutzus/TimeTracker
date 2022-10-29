package ch.zhaw.timetracker.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;

import ch.zhaw.timetracker.enumeration.BookingState;
import ch.zhaw.timetracker.enumeration.BookingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("timeEntry")
public class TimeEntry {
//Basic Variables 
    @Id
    private String timeEntryId;
    @NonNull
    LocalDateTime startPoint;
    Date endPoint;
    @NonNull
    String comment;
    @NonNull
    String userId;

//States
    BookingState bookingState = BookingState.LAUFEND;
    @NonNull
    BookingType bookingType;
}
