package ch.zhaw.timetracker.model;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAddEntryDTO {

	private List<TimeEntry> entries;
}
