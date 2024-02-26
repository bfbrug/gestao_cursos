package com.bfbrug.gestao_cursos.mappers;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.models.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T21:16:09-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toCourse(CourseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Course.CourseBuilder course = Course.builder();

        course.id( dto.getId() );
        course.name( dto.getName() );
        course.description( dto.getDescription() );
        course.startCourse( dto.getStartCourse() );
        course.finalCourse( dto.getFinalCourse() );

        return course.build();
    }

    @Override
    public CourseDTO toDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO.CourseDTOBuilder courseDTO = CourseDTO.builder();

        courseDTO.id( course.getId() );
        courseDTO.name( course.getName() );
        courseDTO.description( course.getDescription() );
        courseDTO.startCourse( course.getStartCourse() );
        courseDTO.finalCourse( course.getFinalCourse() );

        return courseDTO.build();
    }
}
