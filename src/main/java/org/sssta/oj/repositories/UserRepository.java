package org.sssta.oj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.sssta.oj.models.User;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	@Query("select u.id from User  u where u.username = :username")
	Long findIdByUsername(String username);
}
