package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query(value="SELECT * FROM roles WHERE role_name = :name", nativeQuery=true)
    Role getRoleByName(@Param("name") String name);

}
