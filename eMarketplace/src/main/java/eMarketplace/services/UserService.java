package eMarketplace.services;

import eMarketplace.dto.UserJson;
import eMarketplace.models.User;
import eMarketplace.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService
{
	private final UserRepository repository;

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
		User user = new User();
		user.setID(UUID.randomUUID().toString());
		user.setUsername(json.getUsername());
		user.setEmail(json.getEmail());
		user.setPassword(json.getPassword());
		user.setBirthday(json.getBirthday());

		return user;
	}

	private boolean isBad(UserJson json)
	{
		return json.getUsername() == null || json.getUsername().isBlank() ||
			json.getEmail() == null || json.getEmail().isBlank() ||
			json.getPassword() == null || json.getPassword().isBlank() ||
			json.getBirthday() == null || json.getBirthday().isBlank();
	}

	@Transactional
	public void add(UserJson json) throws IllegalArgumentException
	{
		if (isBad(json)) throw new IllegalArgumentException("Bad user request");

		User user = convert(json);
		repository.save(user);
		System.out.println("New user: ");
		printUser(user);
	}

	public String authenticate(UserJson json) throws IllegalAccessException
	{
		User user = convert(json);

		User query = repository.findByUsername(user.getUsername());

		if (user.getPassword().equals(query.getPassword())) return "token";

		throw new IllegalAccessException("Wrong credentials");
	}
}