package com.assignment1.ServiceA.adapter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String id;
    private String name;
    private int age;

}
