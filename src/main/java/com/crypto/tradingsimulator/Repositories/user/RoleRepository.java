package com.crypto.tradingsimulator.Repositories.user;

import com.crypto.tradingsimulator.models.user.Role;
import com.crypto.tradingsimulator.models.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
