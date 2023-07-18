package com.example.swalayan.response;

import com.example.swalayan.model.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable {

    private EmployeeDTO employee;
}
