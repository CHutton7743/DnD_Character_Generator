package com.Crucible.Forge.Interfaces;

import com.Crucible.Forge.Entities.Character;

import java.util.List;

public interface CharacterDao {
    List<Character> findAll();
    Character findById(long id);
    void save(Character character);
    void deleteById(long id);
    void updateByID(Character character);

}
