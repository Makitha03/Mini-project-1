package org.user.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.user.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//finder/query methods
		
		public User findByEmail(String email);
		public List<User> findByFirstNameLike(String firstname);
		public List<User> findByLastNameLike(String lastname);
		
		@Query("select u from User u where u.firstname=?1")
		public List<User> getUsersByFirstName(String firstname);
		
		
		@Query("select u from User u where u.lastname=?1")
		public List<User> getUsersByLastName(String lastname);
		
		
		//JPQL native query
		@Query(value = "select * from user", nativeQuery = true)
		public List<User> getAllUsers();
	
}
