package eMarketplace.controllers;

import eMarketplace.dto.UserJson;
import eMarketplace.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
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
			return ResponseEntity.status(403).build();
		}
	}
}