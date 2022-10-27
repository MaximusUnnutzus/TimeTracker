package ch.zhaw.timetracker.entities;

import java.sql.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TimeEntryCreateDTO {
	private Date startPoint;
	private Date endPoint;
	private String comment;	
}
