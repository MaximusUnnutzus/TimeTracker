package ch.zhaw.timetracker.entities;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("timeEntry")
public class TimeEntry {
    @Id
    private String id;
    @NonNull
    Date startPoint;

    Date endPoint;
    
    @NonNull
    String comment;
   
}
