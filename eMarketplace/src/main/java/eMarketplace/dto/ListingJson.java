package eMarketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListingJson
{
	@JsonProperty("name") private String name;
	@JsonProperty("price") private String price;
	@JsonProperty("description") private String description;
	@JsonProperty("author_username") private String authorUsername;
}