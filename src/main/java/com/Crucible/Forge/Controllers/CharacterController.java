package com.Crucible.Forge.Controllers;
import com.Crucible.Forge.Character_Resources.ForgeLogic;
import com.Crucible.Forge.Character_Resources.SubRace;
import com.Crucible.Forge.Entities.Character;
import com.Crucible.Forge.Entities.User;
import com.Crucible.Forge.Exceptions.UserNotFoundException;
import com.Crucible.Forge.Repositories.CharacterRepository;
import com.Crucible.Forge.Exceptions.CharacterNotFoundException;
import com.Crucible.Forge.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
class CharacterController {
    @Autowired
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final ForgeLogic forgeLogic;
    @Autowired
    private CharacterRepository characterRepository;

    CharacterController() {
        this.forgeLogic = new ForgeLogic();
    }

    @GetMapping("users/{id}/characters")
    List<Character> returnAll(@PathVariable String id) {
        List<Character> characters = new ArrayList<>();
        for (Character character : characterRepository.findAll()) {
            if (character.getUser().getUuid().equals(UUID.fromString(id))) {
                characters.add(character);
            }
        }
        return characters;
    }

    @GetMapping("users/{id}/characters/{characterId}")
    Character findOneCharacterById(@PathVariable String id, @PathVariable long characterId) throws CharacterNotFoundException {
        try {
            for (Character character : characterRepository.findAll()) {
                if (character.getUser().getUuid().equals(UUID.fromString(id)) && character.getId() == characterId) {
                    return character;
                }
            }
        } catch (Exception e) {
            throw new CharacterNotFoundException("Character with id " + characterId + " not found. Or you do not have access to this character.");
        }
        return null;
    }

    @PostMapping("users/{id}/characters")
    Character newCharacter(@RequestBody Character newCharacter, @PathVariable String id) throws UserNotFoundException {
        try {
            for (User user : userRepository.findAll()) {
                if (user.getUuid().equals(UUID.fromString(id))) {
                    newCharacter.setUser(user);
                    if (newCharacter.getId() == null) {
                        newCharacter.setId(characterRepository.count() + 1);
                    }
                }
            }
            return characterRepository.save(newCharacter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCharacter;
    }

    @PutMapping("users/{id}/characters/{characterId}")
    public void replaceCharacter(@RequestBody Character character) throws UserNotFoundException, JsonProcessingException, CharacterNotFoundException {
        Character newCharacter = null;
        try {
            String jsonObject = character.toString();
            newCharacter = new Character();
            newCharacter = mapper.readValue(jsonObject, Character.class);
            Character finalNewCharacter = newCharacter;
            Character characterFromDb = characterRepository.findById(newCharacter.getId())
                    .orElseThrow(() -> new CharacterNotFoundException("Character with id "
                            + finalNewCharacter.getId() + " not found. Or you do not have access to this character."));
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
                characterRepository.save(characterFromDb);
            } else {
                throw new UserNotFoundException("Character with id " + newCharacter.getId() + " not found");
            }
        } catch (Exception e) {
            assert newCharacter != null;
            throw new CharacterNotFoundException("Character with id " + newCharacter.getId() + " not found");
        }
    }

    @DeleteMapping("users/{id}/characters/{characterId}")
    void deleteCharacter(@PathVariable Long characterId, @PathVariable String id) throws CharacterNotFoundException {
        try {
            for (Character character : characterRepository.findAll()) {
                if (character.getId().equals(characterId) && character.getUser().getUuid().equals(UUID.fromString(id))) {
                    characterRepository.delete(character);
                }
            }
        } catch (Exception e) {
            throw new CharacterNotFoundException("Character with id " + characterId + " not found. Or you do not have access to this character.");
        }
    }
}