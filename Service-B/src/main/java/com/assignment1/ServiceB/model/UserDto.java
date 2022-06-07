package com.assignment1.ServiceB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@AllArgsConstructor
@Getter
@ToString
public class UserDto {
    private String id;
    private String name;
    private int age;


}
