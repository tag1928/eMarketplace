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
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
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
	}

	private Listing convert(ListingJson json)
	{
		return new Listing
			(
				UUID.randomUUID().toString(),
				json.getName(),
				json.getPrice(),
				json.getDescription(),
				Instant.now().toString(),
				"photo"
			);
	}

	private ListingJson convert(Listing listing)
	{
		return new ListingJson
			(
				listing.getName(),
				listing.getPrice(),
				listing.getDescription()
			);
	}

	public void init()
	{
		try
		{
			Files.createDirectory(root);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Transactional
	public void add(ListingJson json, MultipartFile file)
	{
		try
		{
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		}
		catch (Exception e)
		{
			if (e instanceof FileAlreadyExistsException)
			{
				throw new RuntimeException("A file of that name already exists.");
			}
			throw new RuntimeException(e.getMessage());
		}

		Listing listing = convert(json);
		listing.setPhotoURL(file.getOriginalFilename());
		repository.save(listing);

		printListing(listing);
	}

	public List<ListingJson> getPage(int pageNumber, int pageSize)
	{
		Sort sort = Sort.by("submissionTime");
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