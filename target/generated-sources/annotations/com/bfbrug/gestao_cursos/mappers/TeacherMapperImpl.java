package com.bfbrug.gestao_cursos.mappers;

import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.models.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T20:19:05-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher toTeacher(TeacherDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setId( dto.getId() );
        teacher.setName( dto.getName() );
        teacher.setEmail( dto.getEmail() );
        teacher.setPassword( dto.getPassword() );

        return teacher;
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
