package codinpad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import codinpad.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
   
    Optional<User> findByEmail(String username);
}