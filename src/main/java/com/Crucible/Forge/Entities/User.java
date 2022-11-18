package com.Crucible.Forge.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="Users")
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long index;
    @Id
    @Column(columnDefinition = "uuid", name = "user_UUID")
    @Type(type="org.hibernate.type.UUIDCharType")
    UUID uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", targetEntity = Character.class)
    @ToString.Exclude
    private List<Character> characters;

    @JsonIgnore
    @ToString.Exclude
    private String password;
}
