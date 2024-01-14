package com.mehdi.school_2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
//    private String username;
//
//    private LocalDate dateOfBirth;
//
//    private int startYear;

    private String address;
//
//    private String gender;
//
//    private String educationId;
//
//    private String healthCareId;
//
//    private String parent1Name;
//
//    private String parent2Name;
//
//    private String parent1Phone;
//
//    private String parent2Phone;
//
//    @JsonProperty("classroom_id")
//    private Long classroomId;
}
