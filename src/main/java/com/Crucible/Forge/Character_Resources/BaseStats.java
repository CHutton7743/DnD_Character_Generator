package com.Crucible.Forge.Character_Resources;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public abstract class BaseStats {
    @ToString.Exclude
    int strength;
    @ToString.Exclude
    int dexterity;
    @ToString.Exclude
    int constitution;
    @ToString.Exclude
    int intelligence;
    @ToString.Exclude
    int wisdom;
    @ToString.Exclude
    int charisma;
}
