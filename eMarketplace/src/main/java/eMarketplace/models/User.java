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
@Table(name = "users")
public class User
{
	@Id
	@Column(name = "id") private String ID;
	@Column(name = "username") private String username;
	@Column(name = "email") private String email;
	@Column(name = "password") private String password;
	@Column(name = "birthday") private String birthday;
}