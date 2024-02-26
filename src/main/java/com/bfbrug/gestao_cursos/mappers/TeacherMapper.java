package com.bfbrug.gestao_cursos.mappers;

import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.models.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {

    Teacher toTeacher(TeacherDTO dto);

    TeacherDTO toDto(Teacher teacher);
}
