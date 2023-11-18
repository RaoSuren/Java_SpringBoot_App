package codinpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codinpad.payloads.ApiResponse;
import codinpad.payloads.UserDTO;
import codinpad.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

     @Autowired
     private UserService userService;

     @PostMapping("/")
     public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createUserDTO = this.userService.createUser(userDTO);

        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
     }

     @PutMapping("/{userId}")
     public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId) {
        UserDTO updatedUserDTO = this.userService.updateUser(userDTO, userId);

        return  ResponseEntity.ok(updatedUserDTO);
     }

     @DeleteMapping("/{userId}")
     public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted suceesfullu", true),HttpStatus.OK);
     }

     @GetMapping("/")
     public ResponseEntity<List<UserDTO>> getAllUsers() {
        
        return  ResponseEntity.ok(this.userService.getAllUsers());
     }

     @GetMapping("/{userId}")
     public ResponseEntity<UserDTO> getSingleUsers(@PathVariable Integer userId) {
        
        return  ResponseEntity.ok(this.userService.getUserById(userId));
     }
     
}