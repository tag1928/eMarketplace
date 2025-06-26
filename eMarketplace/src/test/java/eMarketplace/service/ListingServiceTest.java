package eMarketplace.service;

import eMarketplace.dto.ListingJson;
import eMarketplace.repositories.ListingRepository;
import eMarketplace.services.ListingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListingServiceTest
{
	@Mock
	private ListingRepository repository;

	@InjectMocks
	private ListingService service;

	@Test
	public void addListingTest() throws IOException
	{
		ListingJson goodListing = ListingJson.builder()
			.name("Lawn mower")
			.price("109.99")
			.description("This lawn mower is the best one I got to use in my life.")
			.authorUsername("Fred_169269").build();

		MultipartFile file = mock(MultipartFile.class);
		when(file.getInputStream()).thenReturn(InputStream.nullInputStream());

		Assertions.assertDoesNotThrow(() -> service.addListing(goodListing, file));

		ListingJson badListing1 = ListingJson.builder()
			.name("")
			.price("10.00")
			.description("I dunno")
			.authorUsername("Charles420").build();

		Assertions.assertThrows(IllegalArgumentException.class, () -> service.addListing(badListing1, file));

		ListingJson badListing2 = ListingJson.builder()
			.name("Gun")
			.price("")
			.description("Just take it")
			.authorUsername("Anonymous_notso").build();

		Assertions.assertThrows(IllegalArgumentException.class, () -> service.addListing(badListing2, file));
	}
}