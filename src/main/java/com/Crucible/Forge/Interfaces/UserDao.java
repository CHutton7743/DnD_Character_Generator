package com.Crucible.Forge.Interfaces;

import com.Crucible.Forge.Entities.Character;
import com.Crucible.Forge.Entities.User;
import com.Crucible.Forge.Exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<User> findAll();
    User findById(UUID id) throws UserNotFoundException;
    void save(User user);
    void update(User user);
    void deleteById(long id);
}
