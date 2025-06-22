// src/main/java/com/myapp/schoolhealth/payload/request/MedicineRequest.java
package com.myapp.schoolhealth.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineRequest {
    private String name;
    private String description;
    private Integer quantity;
    private String expiryDate;
}