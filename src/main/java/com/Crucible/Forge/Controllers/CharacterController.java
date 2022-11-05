package com.Crucible.Forge.Controllers;
import com.Crucible.Forge.Entities_and_Repositories.Character;
import com.Crucible.Forge.Entities_and_Repositories.CharacterRepository;
import com.Crucible.Forge.Exceptions.CharacterNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("users/{id}/characters")
class CharacterController {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private final CharacterRepository repository;

    CharacterController(CharacterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("users/{id}/characters")
    List<Character> all() {
        return repository.findAll();
    }

    @PostMapping("users/{id}/characters")
    Character newCharacter(@RequestBody Character newCharacter) {
        return repository.save(newCharacter);
    }


    @GetMapping("users/{id}/characters/{characterId}")
    Character one(@PathVariable Long id) throws CharacterNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @PutMapping("users/{id}/characters/{characterId}")
    Character replaceCharacter(@RequestBody Character newCharacter, @PathVariable Long id) {
    return repository.findById(id)
                .map(character -> {
                    character.setName(newCharacter.getName());
                    character.setGender(newCharacter.getGender());
                    character.setRace(newCharacter.getRace());
                    character.setCharacterClass(newCharacter.getCharacterClass());
                    character.setCharacterLevel(newCharacter.getCharacterLevel());
                    return repository.save(character);
                })
                .orElseGet(() -> {
                    newCharacter.setId(id);
                    return repository.save(newCharacter);
                });
    }

    @DeleteMapping("users/{id}/characters/{characterId}")
    void deleteCharacter(@PathVariable Long id) {
        repository.deleteById(id);
    }
}