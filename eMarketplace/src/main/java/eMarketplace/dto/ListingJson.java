package eMarketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingJson
{
	@JsonProperty("name") private String name;
	@JsonProperty("price") private double price;
	@JsonProperty("description") private String description;
	@JsonProperty("photo_url") private String photoURL;
}