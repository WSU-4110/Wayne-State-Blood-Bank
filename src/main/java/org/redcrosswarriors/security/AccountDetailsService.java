/*
The purpose of this class is to retrieve account information
about a user for login and authorization purposes.
 */
package org.redcrosswarriors.security;
import org.redcrosswarriors.model.Role;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.redcrosswarriors.model.AccountDetails;
import javax.transaction.Transactional;

@Service
public class AccountDetailsService implements UserDetailsService {
    @Autowired
    AccountDetailsRepository repository;

    @Autowired
    RoleRepository roleRepository;

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

    // must be done as a transaction
    @Transactional
    public void createAccount(AccountDetails account) {
        // start by creating the account in the accounts table
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // hash the password so that no one knows what the users password is
        String password = passwordEncoder.encode(account.getPassword());
        // create the account in the account table
        repository.createAccount(account.getEmail(), password);

        AccountDetails createdAccount = repository.findByEmail(account.getEmail());
        int id = createdAccount.getId();

        for (String accountRole : account.getRoles()){
            addRoleByAccountId(id, accountRole);
        }
    }

    public void deleteAccount(String email){
        repository.removeAccountByEmail(email);
    }

    @Transactional
    public void removeRole(String email, String roleName){
        Role role = roleRepository.getRoleByName(roleName);
        AccountDetails account = repository.findByEmail(email);
        if(role != null && account != null){
            repository.removeRole(role.getId(), account.getId());
        }
    }

    @Transactional
    public void addRole(String email, String roleName){
        AccountDetails details = repository.findByEmail(email);
        addRoleByAccountId(details.getId(), roleName);
    }

    private void addRoleByAccountId(int id, String roleName){
        Role role = roleRepository.getRoleByName(roleName);
        // if the role doesn't exist create it
        if(role == null){
            role = new Role();
            role.setRoleName(roleName);
            role = roleRepository.save(role);
        }
        repository.addRole(role.getId(), id);
    }

}


