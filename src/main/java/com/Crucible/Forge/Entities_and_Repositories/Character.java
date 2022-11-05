package com.Crucible.Forge.Entities_and_Repositories;
import com.Crucible.Forge.Character_Resources.*;
import com.Crucible.Forge.Character_Resources.Class;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "characters")
@Data
public class Character extends SubStats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_seq")
    @SequenceGenerator(name = "character_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
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
    private int experience;
    @Column(name = "alignment")
    private Alignment alignment;
    @Column (name = "subrace")
    private SubRace subrace;
    @Column (name = "background")
    private String background;
    @Column (name = "speed")
    private int speed;
    @Column (name = "size")
    private Size size;
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
