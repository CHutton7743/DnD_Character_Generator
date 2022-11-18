package com.Crucible.Forge.Controllers;
import com.Crucible.Forge.Character_Resources.ForgeLogic;
import com.Crucible.Forge.Character_Resources.SubRace;
import com.Crucible.Forge.Entities.Character;
import com.Crucible.Forge.Exceptions.UserNotFoundException;
import com.Crucible.Forge.Repositories.CharacterRepository;
import com.Crucible.Forge.Exceptions.CharacterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.Crucible.Forge.Character_Resources.ForgeLogic;

@RestController
@RequestMapping
class CharacterController {
    @Autowired
    private final ForgeLogic forgeLogic;
    @Autowired
    private final CharacterRepository repository;

    CharacterController(CharacterRepository repository) {
        this.repository = repository;
        this.forgeLogic = new ForgeLogic();
    }

    @GetMapping("users/{id}/characters")
    List<Character> returnAll() {
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
    public void replaceCharacter(@RequestBody Character newCharacter) throws UserNotFoundException {
        Character characterFromDb = repository.findById(newCharacter.getId()).orElseThrow(()
                -> new UserNotFoundException("Character with id " + newCharacter.getId() + " not found"));
        if (characterFromDb != null) {
            characterFromDb.setName(newCharacter.getName());
            characterFromDb.setRace(newCharacter.getRace());
            characterFromDb.setCharacterClass(newCharacter.getCharacterClass());
            characterFromDb.setStrength(newCharacter.getStrength());
            characterFromDb.setDexterity(newCharacter.getDexterity());
            characterFromDb.setConstitution(newCharacter.getConstitution());
            characterFromDb.setIntelligence(newCharacter.getIntelligence());
            characterFromDb.setWisdom(newCharacter.getWisdom());
            characterFromDb.setCharisma(newCharacter.getCharisma());
            characterFromDb.setSpeed(newCharacter.getSpeed());
            characterFromDb.setExperience(newCharacter.getExperience());
            characterFromDb.setAlignment(newCharacter.getAlignment());
            if (newCharacter.getSubrace() != null) {
                characterFromDb.setSubrace(newCharacter.getSubrace());
            }
            characterFromDb.setBackground(newCharacter.getBackground());
            characterFromDb.setSpeed(newCharacter.getSpeed());
            characterFromDb.setSize(newCharacter.getSize());
            forgeLogic.formatStats(characterFromDb);
            forgeLogic.calculateSpeed(characterFromDb);
            if (newCharacter.getSubrace() == SubRace.HighElf) {
                forgeLogic.calculateRacialStats(characterFromDb);
            } else {
                forgeLogic.calculateRacialStats(characterFromDb);
            }
            forgeLogic.calculateSize(characterFromDb);
            forgeLogic.calculateStrengthSubstats(characterFromDb);
            forgeLogic.calculateDexteritySubstats(characterFromDb);
            forgeLogic.calculateIntellectSubstats(characterFromDb);
            forgeLogic.calculateWisdomSubstats(characterFromDb);
            forgeLogic.calculateCharismaSubstats(characterFromDb);
            repository.save(characterFromDb);
        }
    }

    @DeleteMapping("users/{id}/characters/{characterId}")
    void deleteCharacter(@PathVariable Long id) {
        repository.deleteById(id);
    }
}