package eMarketplace.controllers;

import eMarketplace.dto.UserJson;
import eMarketplace.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController
{
	private final UserService service;

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody UserJson json)
	{
		try
		{
			service.add(json);
			return ResponseEntity.ok().build();
		}
		catch (IllegalArgumentException e)
		{
			System.err.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserJson json)
	{
		try
		{
			String token = service.authenticate(json);
			return ResponseEntity.ok().body(token);
		}
		catch (IllegalAccessException e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}