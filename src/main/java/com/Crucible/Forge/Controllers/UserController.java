package com.Crucible.Forge.Controllers;

import com.Crucible.Forge.Entities_and_Repositories.User;
import com.Crucible.Forge.Entities_and_Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
