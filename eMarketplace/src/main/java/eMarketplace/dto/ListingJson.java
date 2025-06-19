package eMarketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListingJson
{
	@JsonProperty("name") private String name;
	@JsonProperty("price") private String price;
	@JsonProperty("description") private String description;
	@JsonProperty("author_username") private String authorUsername;
}