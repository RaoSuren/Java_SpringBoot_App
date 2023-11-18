package codinpad.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import codinpad.exceptions.ResourceNotFoundException;
import codinpad.models.User;
import codinpad.payloads.UserDTO;
import codinpad.repositories.UserRepo;

public class UserService {
     
    @Autowired
    private UserRepo  userRepo;

    public UserDTO createUser(UserDTO userdto)
    {
        User user = this.dtoToUser(userdto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    public UserDTO updateUser(UserDTO userdto, Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAbout(userdto.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDTO updatedDTO = this.userToDto(updatedUser);
        return updatedDTO;
    }

    public UserDTO getUserById(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", " Id ", userId));

        return this.userToDto(user);
    }

    public List<UserDTO> getAllUsers()
    {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDTOs;
    }

    public void deleteUser(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        return user;
    }

    private UserDTO userToDto(User user)
    {
        UserDTO userdto = new UserDTO();
        userdto.setId(user.getId());
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        userdto.setAbout(user.getAbout());
        userdto.setPassword(user.getPassword());

        return userdto;
    }

}