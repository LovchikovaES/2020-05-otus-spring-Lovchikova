package ru.otus.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.DbServiceUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DbServiceUser dbServiceUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = dbServiceUser.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDetailsImpl(user);
    }
}
