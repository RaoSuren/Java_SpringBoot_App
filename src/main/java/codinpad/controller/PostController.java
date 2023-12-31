package codinpad.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import codinpad.payloads.ApiResponse;
import codinpad.payloads.PostDTO;
import codinpad.payloads.PostResponse;
import codinpad.services.FileService;
import codinpad.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController
{

      @Autowired
      private PostService postService;

      @Autowired
      private FileService fileService;

      @Value("${project.image}")
      private String path;

      @PostMapping("/user/{userId}/category/{categoryId}/posts")
      public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto,@PathVariable Integer userId,
        @PathVariable Integer categoryId)
        {
            PostDTO createPost = this.postService.cratePost(postDto, userId, categoryId);

            return new ResponseEntity<PostDTO>(createPost, HttpStatus.OK);
        }

      @GetMapping("/user/{userId}/posts")
      public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId)
      {
          List<PostDTO> postDtos = this.postService.getPostsByUser(userId);

          return new ResponseEntity<List<PostDTO>>(postDtos, HttpStatus.OK);
      }

      @GetMapping("/category/{categoryId}/posts")
      public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId)
      {
          List<PostDTO> postDtos = this.postService.getPostsByCategory(categoryId);

          return new ResponseEntity<List<PostDTO>>(postDtos, HttpStatus.OK);
      }

      @GetMapping("/posts")
      public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
      @RequestParam(value = "pageSize",defaultValue = "10", required = false) Integer pageSize,
      @RequestParam(value = "sortBy",defaultValue = "postId", required = false) String sortBy,
      @RequestParam(value = "sortDir",defaultValue = "asc", required = false) String sortDir
      )
      {
          PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

          return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
      }

      @GetMapping("/posts/{postId}")
      public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId)
      {
          PostDTO postDto = this.postService.getPostbyId(postId);

          return new ResponseEntity<PostDTO>(postDto, HttpStatus.OK);
      }

      @PutMapping("/posts/{postId}")
      public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable Integer postId)
      {
         PostDTO updatePost = this.postService.updatePost(postDto, postId);
         return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
      }

      @DeleteMapping("/posts/{postId}")
      public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
      {
         this.postService.deletePost(postId);
         return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
      }

      @GetMapping("/posts/search/{keywords}")
      public ResponseEntity<List<PostDTO>> searchPosts(@PathVariable String keywords)
      {
            List<PostDTO> result = this.postService.searchPost(keywords);
            return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
      }

      @PostMapping("/post/image/upload/{postId}")
      public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile image,
      @PathVariable Integer postId) throws IOException
      {
          PostDTO postDto = this.postService.getPostbyId(postId);

          String fileName = this.fileService.uploadImage(path, image);
          postDto.setImageName(fileName);
          PostDTO updatePost = this.postService.updatePost(postDto, postId);

          return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
      }

      @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
      public void downloadImage(
        @PathVariable("imageName") String imageName,
        HttpServletResponse response) throws IOException {
          InputStream resource = this.fileService.getResource(path,imageName);
          response.setContentType(MediaType.IMAGE_JPEG_VALUE);
          StreamUtils.copy(resource, response.getOutputStream());
        }

}