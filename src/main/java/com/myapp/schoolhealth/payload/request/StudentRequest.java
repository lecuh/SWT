// src/main/java/com/myapp/schoolhealth/payload/request/StudentRequest.java
package com.myapp.schoolhealth.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String studentCode;
    private String name;
    private String gender;
    private String dateOfBirth;
    private Double height;
    private Double weight;
    private String parentPhone;
    private String className; // ĐÃ THÊM: cho trường lớp
}