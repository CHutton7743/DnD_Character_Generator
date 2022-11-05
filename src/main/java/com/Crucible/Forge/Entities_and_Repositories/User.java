package com.Crucible.Forge.Entities_and_Repositories;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "user", targetEntity = Character.class)
    @ToString.Exclude
    private List<Character> characters;
    private String password;
}
