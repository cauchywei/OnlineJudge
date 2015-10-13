package org.sssta.oj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sssta.oj.models.User;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
