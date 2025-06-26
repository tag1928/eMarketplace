package eMarketplace.services;

import eMarketplace.dto.UserJson;
import eMarketplace.models.User;
import eMarketplace.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.UUID;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserService
{
	private final UserRepository repository;
	private final Pattern pattern = Pattern.compile("^\\w{8,20}$");
	private final EmailValidator emailValidator = EmailValidator.getInstance();

	private void printUser(User user)
	{
		System.out.println("ID: " + user.getID());
		System.out.println("Username: " + user.getUsername());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Birthday: " + user.getBirthday());
	}

	private User convert(UserJson json)
	{
		return User.builder()
			.ID(UUID.randomUUID().toString())
			.username(json.getUsername())
			.email(json.getEmail())
			.password(json.getPassword())
			.birthday(json.getBirthday()).build();
	}

	private boolean isBad(UserJson json)
	{
		if (json.getBirthday() == null || json.getBirthday().isBlank()) return true;
		if (json.getEmail() == null || json.getEmail().isBlank()) return true;

		int year = Integer.parseInt(json.getBirthday().substring(0, 4));

		return json.getUsername() == null || json.getUsername().isBlank() || !pattern.matcher(json.getUsername()).matches() ||
			!emailValidator.isValid(json.getEmail()) ||
			json.getPassword() == null || json.getPassword().isBlank() ||
			Year.now().getValue() - year < 13;
	}

	@Transactional
	public void add(UserJson json) throws IllegalArgumentException
	{
		if (isBad(json))
			throw new IllegalArgumentException("Bad user request");

		if (repository.findByUsername(json.getUsername()) != null)
			throw new IllegalArgumentException("Username is taken");

		if (repository.findByEmail(json.getEmail()) != null)
			throw new IllegalArgumentException("Email already in use");

		User user = convert(json);

		repository.save(user);
		System.out.println("New user: ");
		printUser(user);
	}

	public String authenticate(UserJson json) throws IllegalAccessException
	{
		User user = repository.findByUsername(json.getUsername());

		if (json.getPassword().equals(user.getPassword())) return "token";

		throw new IllegalAccessException("Wrong credentials");
	}
}