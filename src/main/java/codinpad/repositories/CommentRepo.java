package codinpad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import codinpad.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}