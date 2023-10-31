package dev.naman.userservicetestfinal.security;

import dev.naman.userservicetestfinal.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// CustomSpringUserDetails implements UserDetails because in CustomSpringUserDetailsService spring is bydefault returning UserDetails
// which is defined by spring itself. But we want our own CustomUserDetails to show in the output.
// here we will return our own email and password from user db in the form of CustomSpringUserDetails which implemented UserDetails thus
// telling spring to use our own custom implementation rather than the one defined by spring.
public class CustomSpringUserDetails implements UserDetails {
    private User user; // object of User

    public CustomSpringUserDetails(User user) { // constructor dependency injection
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() { // get the password from the database since we have the user object
        return user.getPassword();
    }

    @Override
    public String getUsername() { // get email of the user from database
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { // manually set rest of the fields as true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}