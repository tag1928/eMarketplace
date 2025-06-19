package eMarketplace.repositories;

import eMarketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
	@NativeQuery("SELECT * FROM users WHERE username = ?1")
	User findByUsername(String username);

	@NativeQuery("SELECT * FROM users WHERE email = ?1")
	User findByEmail(String email);
}