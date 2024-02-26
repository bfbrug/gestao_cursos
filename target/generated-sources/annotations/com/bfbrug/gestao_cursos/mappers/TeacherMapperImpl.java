package com.bfbrug.gestao_cursos.mappers;

import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.models.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T21:16:09-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher toTeacher(TeacherDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Teacher.TeacherBuilder teacher = Teacher.builder();

        teacher.id( dto.getId() );
        teacher.name( dto.getName() );
        teacher.email( dto.getEmail() );
        teacher.password( dto.getPassword() );

        return teacher.build();
    }

    @Override
    public TeacherDTO toDto(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDTO.TeacherDTOBuilder teacherDTO = TeacherDTO.builder();

        teacherDTO.id( teacher.getId() );
        teacherDTO.name( teacher.getName() );
        teacherDTO.email( teacher.getEmail() );
        teacherDTO.password( teacher.getPassword() );

        return teacherDTO.build();
    }
}
