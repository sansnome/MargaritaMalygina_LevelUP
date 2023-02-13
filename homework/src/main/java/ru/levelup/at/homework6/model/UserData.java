package ru.levelup.at.homework6.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@ToString

public class UserData {
    public enum Gender {
        female,
        male
    }

    public enum Status {
        active,
        inactive
    }

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
