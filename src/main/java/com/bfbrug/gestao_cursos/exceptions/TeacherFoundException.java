package com.bfbrug.gestao_cursos.exceptions;

public class TeacherFoundException extends RuntimeException {
    public TeacherFoundException(){
        super("Professor já cadastrado com esse email.");
    }
}
