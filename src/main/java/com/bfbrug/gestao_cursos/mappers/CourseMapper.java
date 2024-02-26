package com.bfbrug.gestao_cursos.mappers;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.models.Course;
import org.mapstruct.Mapper;


@Mapper
public interface CourseMapper {

    Course toCourse(CourseDTO dto);

    CourseDTO toDto(Course course);
}
