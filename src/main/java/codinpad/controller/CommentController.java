package codinpad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codinpad.payloads.ApiResponse;
import codinpad.payloads.CommentDTO;
import codinpad.services.CommentService;

@RestController
@RequestMapping
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto, @PathVariable Integer commentId)
    {
       CommentDTO comment = this.commentService.createComment(commentDto, commentId);
       return new ResponseEntity<CommentDTO>(commentDto, HttpStatus.OK);
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
       this.commentService.deleteComment(commentId);
       return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted successfully", true), HttpStatus.OK);
    }
}