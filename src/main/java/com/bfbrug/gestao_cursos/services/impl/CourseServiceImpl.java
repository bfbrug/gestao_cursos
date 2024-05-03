package com.bfbrug.gestao_cursos.services.impl;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.exceptions.NotFoundException;
import com.bfbrug.gestao_cursos.mappers.CourseMapper;
import com.bfbrug.gestao_cursos.models.Course;
import com.bfbrug.gestao_cursos.repositories.CourseRepository;
import com.bfbrug.gestao_cursos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {

        Course course = courseMapper.toCourse(courseDTO);
        Course savedCourse = courseRepository.save(course);

        return courseMapper.toDto(savedCourse);
    }

    @Override
    public CourseDTO getCourseById(UUID courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course is not exist with given id: " + courseId));

        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(UUID courseId, CourseDTO courseDTO) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course is not exist with given id: " + courseId));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setStartCourse(courseDTO.getStartCourse());
        course.setFinalCourse(courseDTO.getFinalCourse());

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toDto(updatedCourse);
    }

    @Override
    public void deleteCourse(UUID courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course is not exist with given id: " + courseId.toString()));

        courseRepository.deleteById(courseId);
    }
}
