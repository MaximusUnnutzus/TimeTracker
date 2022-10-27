package ch.zhaw.timetracker.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCreateDTO {
	private String loginname;
	private String surname;
	private String name;
	private String password;
	private String email;
}
