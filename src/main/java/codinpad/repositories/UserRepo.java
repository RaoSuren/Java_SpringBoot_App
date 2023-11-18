package codinpad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import codinpad.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}