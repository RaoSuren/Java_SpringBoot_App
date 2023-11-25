package codinpad.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import codinpad.exceptions.ResourceNotFoundException;
import codinpad.models.Category;
import codinpad.models.Post;
import codinpad.models.User;
import codinpad.payloads.PostDTO;
import codinpad.repositories.CategoryRepo;
import codinpad.repositories.PostRepo;
import codinpad.repositories.UserRepo;

public class PostService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public PostDTO cratePost(PostDTO postDto, Integer userId, Integer categoryId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User with Id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category with Id", categoryId));


        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("Default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(post, PostDTO.class);

    }

    public List<PostDTO> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<Post> posts = this.postRepo.findAllByCategory(category);
        List<PostDTO> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDtos;
    }

    public List<PostDTO> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user Id", userId));

        List<Post> posts = this.postRepo.findAllByUser(user);

        List<PostDTO> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDtos;
    }

    public List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize)
    {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Post> pageContent = this.postRepo.findAll(p);
        List<Post> posts = pageContent.getContent();

        List<PostDTO> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDtos;
    }

    public PostDTO getPostbyId(Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        return this.modelMapper.map(post, PostDTO.class);
    }

    public PostDTO updatePost(PostDTO postDto, Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    public void deletePost(Integer postId)
    {
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
      this.postRepo.delete(post);
    }

}