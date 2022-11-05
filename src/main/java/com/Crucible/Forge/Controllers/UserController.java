package com.Crucible.Forge.Controllers;

import com.Crucible.Forge.Entities_and_Repositories.User;
import com.Crucible.Forge.Entities_and_Repositories.UserRepository;
import com.Crucible.Forge.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    List<User> all(@PathVariable String id) {
        return userRepository.findAll();
    }

    @PostMapping
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/{id}")
    User one(@PathVariable Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
