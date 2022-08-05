package com.Crucible.Controllers;

import java.util.List;

import com.Crucible.Character_Resources.Character;
import com.Crucible.Character_Resources.CharacterRepository;
import com.Crucible.Exceptions.CharacterNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CharacterController {

    private final CharacterRepository repository;

    CharacterController(CharacterRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Character> all() {
        return (List<Character>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/employees")
    Character newEmployee(@RequestBody Character newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Character one(@PathVariable String id) throws CharacterNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Character replaceEmployee(@RequestBody Character newCharacter, @PathVariable String id) {

        return repository.findById(id)
                .map(Character -> {
                    Character.setName(newCharacter.getName());
                    Character.setCharacterClass(newCharacter.getCharacterClass());
                    return repository.save(Character);
                })
                .orElseGet(() -> {
                    newCharacter.setName(id);
                    return repository.save(newCharacter);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable String id) {
        repository.deleteById(id);
    }
}