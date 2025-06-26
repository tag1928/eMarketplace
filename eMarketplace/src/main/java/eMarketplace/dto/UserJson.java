package eMarketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserJson
{
	@JsonProperty("username") private String username;
	@JsonProperty("email") private String email;
	@JsonProperty("password") private String password;
	@JsonProperty("birthday") private String birthday;
}