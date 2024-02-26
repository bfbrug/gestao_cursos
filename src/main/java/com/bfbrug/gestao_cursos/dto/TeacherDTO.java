package com.bfbrug.gestao_cursos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private UUID id;

    @NotBlank()
    private String name;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 6, max = 50, message = "A senha deve ter entre 6 a 50 caracteres")
    private String password;
}
