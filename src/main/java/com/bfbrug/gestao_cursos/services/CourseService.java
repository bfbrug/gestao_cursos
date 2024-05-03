package com.bfbrug.gestao_cursos.services;

import com.bfbrug.gestao_cursos.dto.CourseDTO;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(UUID courseId);

    List<CourseDTO> getAllCourses();

    CourseDTO updateCourse(UUID courseId, CourseDTO courseDTO);

    void deleteCourse(UUID courseId);

}
