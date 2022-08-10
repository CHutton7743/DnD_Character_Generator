package com.Crucible.Forge.Controllers;

import com.Crucible.Forge.Entities_and_Repositories.User;
import com.Crucible.Forge.Entities_and_Repositories.UserRepository;
import com.Crucible.Forge.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("users")
    public List<User> getUsers() {
        return this.repository.findAll();
    }
    @PostMapping("users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }
    @GetMapping("users/{id}")
    User one(@PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    @PutMapping("users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(User -> {
                    User.setFirstName(newUser.getFirstName());
                    User.setLastName(newUser.getLastName());
                    User.setEmail(newUser.getEmail());
                    return repository.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }
    @DeleteMapping("users/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
