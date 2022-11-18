package com.Crucible.Forge.Repositories;

import com.Crucible.Forge.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByuuid (UUID uuid);
}
