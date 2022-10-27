package ch.zhaw.timetracker.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("user")
public class User {
	@Id
	private String id;
	@NonNull
	private String loginname;
	@NonNull
	private String surname;
	@NonNull
	private String name;
	@NonNull
	private String password;
	@NonNull
	private String email;
}
