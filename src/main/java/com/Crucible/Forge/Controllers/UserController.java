package com.Crucible.Forge.Controllers;

import com.Crucible.Forge.Entities.User;
import com.Crucible.Forge.Repositories.UserRepository;
import com.Crucible.Forge.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        if (newUser.getUuid() == null) {
            newUser.setUuid(java.util.UUID.randomUUID());
        }
        newUser.setIndex(userRepository.count() + 1);
        return userRepository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User findOneUserById(@PathVariable String id) throws UserNotFoundException {
        UUID uuid = UUID.fromString(id);
        return userRepository.findByuuid(uuid);
    }

    @PutMapping("/users/{id}")
    void replaceUser(@RequestBody User newUser, @PathVariable String id) throws UserNotFoundException {
        for(User user : userRepository.findAll()) {
            if(user.getUuid().equals(UUID.fromString(id))) {
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setEmail(newUser.getEmail());
                user.setPassword(newUser.getPassword());
                user.setCharacters(newUser.getCharacters());
                userRepository.save(user);
                break;
            }
        }
    }
    // write a delete method to delete from user by uuid
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable String id) throws UserNotFoundException {
        for (User user : userRepository.findAll()) {
            if (user.getUuid().equals(UUID.fromString(id))) {
                userRepository.delete(user);
                break;
            }
        }
    }
}
