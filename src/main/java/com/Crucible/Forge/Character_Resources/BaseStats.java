package com.Crucible.Forge.Character_Resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public abstract class BaseStats {
    @JsonIgnore
    int strength;
    @JsonIgnore
    int dexterity;
    @JsonIgnore
    int constitution;
    @JsonIgnore
    int intelligence;
    @JsonIgnore
    int wisdom;
    @JsonIgnore
    int charisma;
}
