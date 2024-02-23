package com.bfbrug.gestao_cursos.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String message;
    private String field;

}
