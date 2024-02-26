package com.bfbrug.gestao_cursos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Date startCourse;

    @NotNull
    private Date finalCourse;
}
