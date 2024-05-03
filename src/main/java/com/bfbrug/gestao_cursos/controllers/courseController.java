package com.bfbrug.gestao_cursos.controllers;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.exceptions.NotFoundException;
import com.bfbrug.gestao_cursos.services.CourseService;
import com.bfbrug.gestao_cursos.services.CourseService_;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class courseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO){
        CourseDTO savedCourse = courseService.createCourse(courseDTO);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") UUID courseId){
        CourseDTO courseDTO = courseService.getCourseById(courseId);

        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){

        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("{id}")
    public ResponseEntity<CourseDTO> updateCourseById(@PathVariable("id") UUID courseId,  @RequestBody CourseDTO courseDTO){
        CourseDTO updateCourseDTO = courseService.updateCourse(courseId, courseDTO);

        return ResponseEntity.ok(updateCourseDTO);
    }

    @DeleteMapping(value = "{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("courseId") UUID courseId) {

        courseService.deleteCourse(courseId);

        return ResponseEntity.ok("Course deleted successfully!");
    }
}
