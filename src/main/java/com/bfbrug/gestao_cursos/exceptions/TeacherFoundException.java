package com.bfbrug.gestao_cursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeacherFoundException extends RuntimeException {
    public TeacherFoundException(){
        super("Professor já cadastrado com esse email.");
    }
}
