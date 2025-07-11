package eMarketplace.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name = "listings")
public class Listing
{
	@Id
	@Column(name = "id") private String ID;
	@Column(name = "name") private String name;
	@Column(name = "price") private double price;
	@Column(name = "description") private String description;
	@Column(name = "submission_time") private String submissionTime;
	@Column(name = "photo_url") private String photoURL;
	@Column(name = "author_username") private String authorUsername;
}