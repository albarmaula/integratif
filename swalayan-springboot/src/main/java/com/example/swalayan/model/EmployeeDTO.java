package com.example.swalayan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    private Long nip;
    private String name;
    private String username;
    private String password;
    private String address;
    private String number_phone;
    private String dept_name;
    private String position;
}
