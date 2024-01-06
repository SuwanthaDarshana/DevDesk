package com.devstack.healthcare.system.dto.request;

import lombok.*;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RequestDoctorDto {
    private String name;
    private String address;
    private String contact;
    private double salary;
}
