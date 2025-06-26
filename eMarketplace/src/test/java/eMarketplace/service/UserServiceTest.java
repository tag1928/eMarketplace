package eMarketplace.service;

import eMarketplace.dto.UserJson;
import eMarketplace.repositories.UserRepository;
import eMarketplace.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserService service;

	@Test
	public void addUserTest()
	{
		UserJson goodUser = UserJson.builder()
			.username("Tyrone169269")
			.email("thiefshop@gmail.com")
			.password("very secure password")
			.birthday("1993-01-3").build();

		Assertions.assertDoesNotThrow(() -> service.add(goodUser));

		UserJson badUser1 = UserJson.builder()
			.username("")
			.email("dorothy@gmail.com")
			.password("password1234")
			.birthday("1980-9-24").build();

		Assertions.assertThrows(IllegalArgumentException.class, () -> service.add(badUser1));

		UserJson badUser2 = UserJson.builder()
			.username("Bobloxer1234")
			.email("roadblocks*whomail.com")
			.password("password1234")
			.birthday("1980-9-24").build();

		Assertions.assertThrows(IllegalArgumentException.class, () -> service.add(badUser2));

		UserJson badUser3 = UserJson.builder()
			.username("JackTheGripper")
			.email("geneva@gmail.com")
			.password("Jdio1-alkjd-191923-sd")
			.birthday("2020-3-28").build();

		Assertions.assertThrows(IllegalArgumentException.class, () -> service.add(badUser3));
	}
}