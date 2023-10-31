package dev.naman.userservicetestfinal.security;

import dev.naman.userservicetestfinal.models.User;
import dev.naman.userservicetestfinal.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// this class is implementing UserDetailsService interface which is used to tell spring that we want to
// authenticate(i.e login etc) by using our own fields from the database and not from the inmemory userdetails provided by spring
// by implementing UserDetailsService we will implement the loadUserByUserName(String name) amd we will tell spring
// that we want email as username and password as bcrypt password from database
@Service // used to tell spring that we have a special class and create an object of this class at starting of spring boot application
public class CustomSpringUserDetailsService
        implements UserDetailsService {
    private UserRepository userRepository;

    public CustomSpringUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // here we will provide our email in loadUserByUsername(String userName) fetched from db
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User doesn't exist");
        }

        User user = userOptional.get();
        return new CustomSpringUserDetails(user); // defined our our CustomUserDetails object and get our data from db instead of spring memory
    }
}