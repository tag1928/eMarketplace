package eMarketplace.services;

import eMarketplace.dto.ListingJson;
import eMarketplace.models.Listing;
import eMarketplace.repositories.ListingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ListingService
{
	private final ListingRepository repository;

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
		Listing output = new Listing();

		output.setID(UUID.randomUUID().toString());
		output.setName(json.getName());
		output.setPrice(json.getPrice());
		output.setDescription(json.getDescription());
		output.setSubmissionTime(Instant.now().toString());
		output.setPhotoURL(json.getPhotoURL());

		return output;
	}

	private ListingJson convert(Listing listing)
	{
		ListingJson output = new ListingJson();

		output.setName(listing.getName());
		output.setPrice(listing.getPrice());
		output.setDescription(listing.getDescription());
		output.setPhotoURL(listing.getPhotoURL());

		return output;
	}

	@Transactional
	public void add(ListingJson json)
	{
		Listing listing = convert(json);
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