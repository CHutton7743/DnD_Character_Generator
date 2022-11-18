package com.Crucible.Forge.Daos;

import com.Crucible.Forge.Entities.Character;
import com.Crucible.Forge.Interfaces.CharacterDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CharacterDaoImpl implements CharacterDao {
    private List<Character> characters = new ArrayList<>();


    public List<Character> findAll() {
        return characters;
    }


    public Character findById(long id) {
        for (Character character : characters) {
            if (Objects.equals(character.getId(), id)) {
                return character;
            }
        }
        return null;
    }


    public void save(Character character) {
        if (character.getId().equals(null)) {
            character.setId((long) (characters.size() + 1));
        }
        characters.add(character);
    }

    public void deleteById(long id) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getId().equals(id)) {
                characters.remove(i);
                // decrement all the ids after the deleted one
                for (int j = i; j < characters.size(); j++) {
                    characters.get(j).setId(characters.get(j).getId() - 1);
                }
            }
        }
    }


    public void updateByID(Character character) {
        for (int i = 0; i < characters.size(); i++) {
            if (Objects.equals(characters.get(i).getId(), character.getId())) {
                character.setId(characters.get(i).getId());
                characters.set(i, character);
                break;
            }
        }
    }
}
