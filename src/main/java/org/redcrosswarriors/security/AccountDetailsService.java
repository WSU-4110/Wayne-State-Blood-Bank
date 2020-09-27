/*
The purpose of this class is to retrieve account information
about a user for login and authorization purposes.
 */
package org.redcrosswarriors.security;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.redcrosswarriors.model.AccountDetails;

@Service
public class AccountDetailsService implements UserDetailsService {
    @Autowired
    AccountDetailsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDetails accountDetails = repository.findByEmail(username);

        UserBuilder builder = null;
        if (accountDetails != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(accountDetails.getPassword());
            builder.roles(accountDetails.getRoles());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

}


