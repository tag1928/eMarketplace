package eMarketplace.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eMarketplace.dto.ListingJson;
import eMarketplace.services.ListingService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/listing")
public class ListingController
{
	private final ListingService service;
	private final ObjectMapper mapper;

	@PostConstruct
	public void init()
	{
		service.init();
	}

	@GetMapping("/{pageNumber}")
	public ResponseEntity<List<ListingJson>> get(@PathVariable int pageNumber)
	{
		return ResponseEntity.ok(service.getPage(pageNumber, 6));
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestPart("json") String json, @RequestPart("file") MultipartFile file)
	{
		try
		{
			service.addListing(mapper.readValue(json, ListingJson.class), file);
		}
		catch (Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
}