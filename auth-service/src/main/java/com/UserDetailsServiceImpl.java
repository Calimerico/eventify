package com;

/**
 * Created by spasoje on 25-Nov-18.
 */
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // hard coding the users. All passwords must be encoded.
        final User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        //TODO Check password here, wra re chekcing just username!
        // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
        // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

        // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
        // And used by auth manager to verify and check user authentication.
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
