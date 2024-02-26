package com.bfbrug.gestao_cursos.controllers;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.exceptions.NotFoundException;
import com.bfbrug.gestao_cursos.services.CourseService;
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
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@Validated @RequestBody CourseDTO courseDTO){
        return courseService.createCourse(courseDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> list(){
        return courseService.listCourse();
    }

    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO getCourseById(@PathVariable(value = "courseId") UUID courseId){
        return courseService.findCourseById(courseId).orElseThrow(NotFoundException::new);
    }


    @PutMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseDTO> updatedCourseById(
            @Validated @PathVariable("courseId") UUID courseId, @RequestBody CourseDTO courseDTO) {

        return courseService.updateCourseById(courseId, courseDTO);
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteCourseById(@PathVariable("courseId") UUID courseId) {

        if(!courseService.deleteCourseById(courseId)){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
