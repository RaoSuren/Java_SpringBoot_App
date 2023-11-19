package codinpad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import codinpad.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}