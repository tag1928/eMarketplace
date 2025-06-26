package eMarketplace.services;

import eMarketplace.dto.ListingJson;
import eMarketplace.models.Listing;
import eMarketplace.repositories.ListingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ListingService
{
	private final ListingRepository repository;
	private final Path root = Paths.get("uploads");

	private void printListing(Listing listing)
	{
		System.out.println("ID: " + listing.getID());
		System.out.println("Name: " + listing.getName());
		System.out.println("Price: " + listing.getPrice());
		System.out.println("Description: " + listing.getDescription());
		System.out.println("Submission time: " + listing.getSubmissionTime());
		System.out.println("Photo URL: " + listing.getPhotoURL());
		System.out.println("Author username: " + listing.getAuthorUsername());
	}

	public void init()
	{
		try
		{
			Files.createDirectories(root);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	private Listing convert(ListingJson json, String photoURL)
	{
		return Listing.builder()
			.name(json.getName())
			.price(Double.parseDouble(json.getPrice()))
			.description(json.getDescription())
			.submissionTime(Date.from(Instant.now()).toString())
			.photoURL(photoURL)
			.authorUsername(json.getAuthorUsername())
			.build();
	}

	private ListingJson convert(Listing listing)
	{
		return ListingJson.builder()
			.name(listing.getName())
			.price("" + listing.getPrice())
			.description(listing.getDescription())
			.authorUsername(listing.getAuthorUsername())
			.build();
	}

	private boolean isBad(ListingJson json)
	{
		return json.getName() == null || json.getName().isBlank() ||
			json.getPrice() == null || json.getPrice().isBlank() ||
			json.getAuthorUsername() == null || json.getAuthorUsername().isBlank();
	}

	@Transactional
	public void addListing(ListingJson json, MultipartFile file) throws IllegalArgumentException
	{
		if (isBad(json))
			throw new IllegalArgumentException("Bad listing request");

		String fileName = UUID.randomUUID().toString() + ".jpg";

		try
		{
			Files.copy(file.getInputStream(), root.resolve(fileName));
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}

		Listing listing = convert(json, fileName);
		repository.save(listing);
		System.out.println("New listing: ");
		printListing(listing);
	}

	public List<ListingJson> getPage(int pageNumber, int pageSize, String sortBy)
	{
		Sort sort;

		if (sortBy.equals("date_desc")) sort = Sort.by("submissionTime").descending();
		else if (sortBy.equals("date_asc")) sort = Sort.by("submissionTime").ascending();
		else throw new IllegalArgumentException("Bad sort parameter");

		PageRequest page = PageRequest.of(pageNumber, pageSize, sort);

		List<Listing> query = repository.findAll(page).getContent();

		List<ListingJson> output = new ArrayList<>();

		for (Listing x : query)
		{
			output.add(convert(x));
		}

		return output;
	}
}