package by.romanovich.it.authentication;

import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("authService")
public class AuthenticationService implements UserDetailsService {

    private Logger log = Logger.getLogger(AuthenticationService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByUserName(userName);
            if(user == null)
                throw new UsernameNotFoundException("name not found");
        } catch (ServiceException e) {
            log.info("Cannot get user by name");
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority("ROLE_PRIVATE"));
        return authorities;
    }
}
