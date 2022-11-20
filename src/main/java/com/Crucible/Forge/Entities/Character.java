package com.Crucible.Forge.Entities;
import com.Crucible.Forge.Character_Resources.*;
import com.Crucible.Forge.Character_Resources.Class;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "characters")
@Data
public class Character extends SubStats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "race")
    private Race race;
    @Column(name = "class")
    private Class characterClass;
    @Column(name = "level")
    private int characterLevel;
    @Column(name = "experience")
    @JsonIgnore
    private int experience;
    @Column(name = "alignment")
    @JsonIgnore
    private Alignment alignment;
    @Column (name = "subrace")
    @JsonIgnore
    private SubRace subrace;
    @Column (name = "background")
    @JsonIgnore
    private String background;
    @Column (name = "speed")
    @JsonIgnore
    private int speed;
    @Column (name = "size")
    @JsonIgnore
    private Size size;
}
