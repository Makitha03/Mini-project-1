package org.user.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.user.app.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
