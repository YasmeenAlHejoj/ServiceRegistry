package com.assignment1.ServiceA.model;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@ToString
public class User {
    private String id;
    private String name;
    private int age;
}
