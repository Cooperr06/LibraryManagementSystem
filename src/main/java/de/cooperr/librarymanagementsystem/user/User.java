package de.cooperr.librarymanagementsystem.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "user")
public class User {

    @Id
    private UUID userId = UUID.randomUUID();
    private String name;
    private String joinedAt;
    private String birthday;
}
