package com.Crucible.Forge.Entities_and_Repositories;
import com.Crucible.Forge.Character_Resources.*;
import com.Crucible.Forge.Character_Resources.Class;
import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Character extends SubStats {
    @Column(name = "name")
    private String name;
    private int speed;
    private Size size;
    @Column(name = "gender")
    private String gender;
    @Column(name = "race")
    private Race race;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "class")
    private Class characterClass;
    @Column(name = "level")
    private int characterLevel;
    private int experience;
    private Alignment alignment;
    private SubRace subrace;
    private String background;
    public Character() {

    }

    public Character(String name, String gender, String race, Long id, String characterClass, int characterLevel) {
        this.name = name;
        this.gender = gender;
        this.race = Race.valueOf(capitalize(race));
        this.id = id;
        this.characterClass = Class.valueOf(capitalize(characterClass));
        this.characterLevel = characterLevel;
    }

    public Character(String name, String gender, String race, Long id, String characterClass, String subrace) {
        this.name = name;
        this.gender = gender;
        this.race = Race.valueOf(capitalize(race));
        this.id = id;
        this.characterClass = Class.valueOf(capitalize(characterClass));
        this.subrace = SubRace.valueOf(capitalize(subrace));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public String toString() {
        return "Character{" +
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
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
