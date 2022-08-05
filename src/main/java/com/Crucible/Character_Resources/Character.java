package com.Crucible.Character_Resources;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Character extends SubStats {
    private int speed;
    private Size size;
    private Gender gender;
    private Race race;
    @Id
    private String name;
    private Class characterClass;
    private int characterLevel;
    private int experience;
    private Alignment alignment;
    private SubRace subrace;

    public Character() {

    }

    public int getSpeed() {
        return speed;
    }

    public Character(Gender gender, Race race, String name, Class characterClass, int characterLevel) {
        this.gender = gender;
        this.race = race;
        this.name = name;
        this.characterClass = characterClass;
        this.characterLevel = characterLevel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Class characterClass) {
        this.characterClass = characterClass;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public SubRace getSubrace() {
        return subrace;
    }

    public void setSubrace(SubRace subrace) {
        this.subrace = subrace;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name=" + name +
                ", race='" + race.toString() + '\'' +
                ", characterClass='" + characterClass.toString() + '\'' +
                ", gender='" + gender.toString() + '\'' +
                ", subrace='" + subrace.toString() + '\'' +
                ", level='" + characterLevel + '\'' +
                ", size='" + size.toString() + '\'' +
                ", speed='" + speed + '\'' +
                ", experience='" + experience + '\'' +
                ", alignment='" + alignment.toString() + '\'' +
                ", size='" + size.toString() + '\'' +
                '}';
    }
}
