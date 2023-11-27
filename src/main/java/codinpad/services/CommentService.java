package codinpad.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codinpad.exceptions.ResourceNotFoundException;
import codinpad.models.Comment;
import codinpad.models.Post;
import codinpad.payloads.CommentDTO;
import codinpad.repositories.CommentRepo;
import codinpad.repositories.PostRepo;

@Service
public class CommentService
{
      @Autowired
      private PostRepo postRepo;

      @Autowired
      private CommentRepo commentRepo;

      @Autowired
      private ModelMapper modelMapper;

      public CommentDTO createComment(CommentDTO commentDto, Integer postId)
      {

         Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
         Comment comment = this.modelMapper.map(commentDto, Comment.class);
         comment.setPost(post);
         Comment result = this.commentRepo.save(comment);
         return this.modelMapper.map(comment, CommentDTO.class);
      }

      public void deleteComment(Integer commentId)
      {

          Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "Comment Id", commentId));

          this.commentRepo.delete(comment);
      }
}