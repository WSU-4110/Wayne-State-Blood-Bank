package org.redcrosswarriors.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.redcrosswarriors.model.AccountDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;


@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetails, String> {

    @Query(value="SELECT * FROM account_details WHERE email = :email", nativeQuery=true)
    AccountDetails findByEmail(@Param("email") String email);

    @Query(value="SELECT id FROM accounts WHERE email = :email", nativeQuery=true)
    int findIdByEmail(@Param("email") String email);

    @Modifying
    @Query(value= "INSERT INTO accounts(email, password) VALUES(:email, :password)", nativeQuery = true)
    void createAccount(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Query(value = "INSERT INTO account_roles VALUES(:role_id, :account_id)", nativeQuery = true)
    void addRole(@Param("role_id") int roleId, @Param("account_id") int accountId);

    @Modifying
    @Query(value="DELETE FROM account_roles WHERE account_id = :account_id AND role_id = :role_id", nativeQuery = true)
    void removeRole(@Param("role_id") int roleId, @Param("account_id") int accountId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM accounts WHERE email = :email", nativeQuery = true)
    void removeAccountByEmail(@Param("email") String email);

    @Modifying
    @Query(value="UPDATE accounts SET password = :password WHERE email = :email", nativeQuery=true)
    void updatePassword(@Param("email") String email, @Param("password") String password);

}
