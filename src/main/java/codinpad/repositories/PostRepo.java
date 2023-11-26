package codinpad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codinpad.models.Category;
import codinpad.models.Post;
import codinpad.models.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
      List<Post> findAllByUser(User user);
      List<Post> findAllByCategory(Category category);
      List<Post> findByTitleContaining(String title);
}