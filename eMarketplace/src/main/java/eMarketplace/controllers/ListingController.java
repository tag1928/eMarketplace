package eMarketplace.controllers;

import eMarketplace.dto.ListingJson;
import eMarketplace.services.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/listing")
public class ListingController
{
	private final ListingService service;

	@GetMapping("/{pageNumber}")
	public ResponseEntity<List<ListingJson>> get(@PathVariable int pageNumber)
	{
		return ResponseEntity.ok(service.getPage(pageNumber, 6));
	}

	@GetMapping("/listing.html")
	public ResponseEntity<Void> asdf()
	{
		System.out.println("laskjdf");
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody ListingJson json)
	{
		service.add(json);
		return ResponseEntity.noContent().build();
	}
}