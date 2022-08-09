package com.Crucible.Forge.Controllers;

import java.util.List;

import com.Crucible.Forge.Entities_and_Repositories.Character;
import com.Crucible.Forge.Entities_and_Repositories.CharacterRepository;
import com.Crucible.Forge.Exceptions.CharacterNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
class CharacterController {
    private final CharacterRepository repository;
    CharacterController(CharacterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("characters")
    List<Character> all() {
        return (List<Character>) repository.findAll();
    }
    // end::get-aggregate-root[]
    @PostMapping("characters")
    Character newCharacter(@RequestBody Character newCharacter) {
        return repository.save(newCharacter);
    }
    // Single item
    @GetMapping("characters/{id}")
    Character one(@PathVariable String id) throws CharacterNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }
    @PutMapping("characters/{id}")
    Character replaceCharacter(@RequestBody Character newCharacter, @PathVariable String id) {
        return repository.findById(id)
                .map(Character -> {
                    Character.setName(newCharacter.getName());
                    Character.setCharacterClass(newCharacter.getCharacterClass());
                    Character.setGender(newCharacter.getGender());
                    Character.setRace(newCharacter.getRace());
                    Character.setCharacterLevel(newCharacter.getCharacterLevel());
                    if (newCharacter.getAlignment()!= null) {
                    Character.setAlignment(newCharacter.getAlignment());
                    }
                    if (newCharacter.getSubrace() != null) {
                        Character.setSubrace(newCharacter.getSubrace());
                    }
                    Character.setSize(newCharacter.getSize());
                    return repository.save(Character);
                })
                .orElseGet(() -> {
                    newCharacter.setName(id);
                    return repository.save(newCharacter);
                });
    }
    @DeleteMapping("characters/{id}")
    void deleteCharacter(@PathVariable String id) {
        repository.deleteById(id);
    }
}