package com.Crucible.Forge.Character_Resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class SubStats extends BaseStats {
    @ToString.Exclude
    @JsonIgnore
    private int acrobatics;
    @ToString.Exclude
    @JsonIgnore
    private int animalHandling;
    @ToString.Exclude
    @JsonIgnore
    private int arcana;
    @ToString.Exclude
    @JsonIgnore
    private int athletics;
    @ToString.Exclude
    @JsonIgnore
    private int deception;
    @ToString.Exclude
    @JsonIgnore
    private int history;
    @ToString.Exclude
    @JsonIgnore
    private int insight;
    @ToString.Exclude
    @JsonIgnore
    private int intimidation;
    @ToString.Exclude
    @JsonIgnore
    private int investigation;
    @ToString.Exclude
    @JsonIgnore
    private int medicine;
    @ToString.Exclude
    @JsonIgnore
    private int nature;
    @ToString.Exclude
    @JsonIgnore
    private int perception;
    @ToString.Exclude
    @JsonIgnore
    private int performance;
    @ToString.Exclude
    @JsonIgnore
    private int persuasion;
    @ToString.Exclude
    @JsonIgnore
    private int religion;
    @ToString.Exclude
    @JsonIgnore
    private int sleightOfHand;
    @ToString.Exclude
    @JsonIgnore
    private int stealth;
    @ToString.Exclude
    @JsonIgnore
    private int survival;
}
