package com.Crucible.Forge.Daos;
import com.Crucible.Forge.Entities.User;
import com.Crucible.Forge.Exceptions.UserNotFoundException;
import com.Crucible.Forge.Interfaces.UserDao;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl implements UserDao {
    private static final List<User> PEOPLE = new ArrayList<>();
    public List<User> findAll() {
        return PEOPLE;
    }
    public User findById(UUID id) throws UserNotFoundException {
        for (User person : PEOPLE) {
            if (person.getUuid().equals(id)) {
                return person;
            }
        }
        throw new UserNotFoundException("User with ID " + id + " was not found");
    }

    public void deleteById(long id) {
        for (int i = 0; i < PEOPLE.size(); i++) {
            if (PEOPLE.get(i).getUuid().equals(id)) {
                PEOPLE.remove(i);
                // decrement all the ids after the deleted one
                for (int j = i; j < PEOPLE.size(); j++) {
                    PEOPLE.get(j).setIndex(PEOPLE.get(j).getIndex() - 1);
                }
            }
        }
    }
    public void save(User user) {
        if (user.getUuid().equals(null)) {
            user.setIndex(PEOPLE.size() + 1);
        }
        PEOPLE.add(user);
    }
    public void update(User user) {
        for (int i = 0; i < PEOPLE.size(); i++) {
            if (PEOPLE.get(i).getUuid().equals(user.getUuid())) {
                user.setIndex(PEOPLE.get(i).getIndex());
                PEOPLE.set(i, user);
                break;
            }
        }
    }
    public void updateById(User user) {
        for (User u : PEOPLE) {
            if (u.getIndex() == user.getIndex()) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setEmail(user.getEmail());
                u.setCharacters(user.getCharacters());
                u.setPassword(user.getPassword());
                u.setUuid(user.getUuid());
                break;
            }
        }
    }
}
