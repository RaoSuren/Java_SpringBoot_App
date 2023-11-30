package codinpad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import codinpad.exceptions.ResourceNotFoundException;
import codinpad.models.User;
import codinpad.repositories.UserRepo;

public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user", "user email "+username, 0));

        return user;
    }
}