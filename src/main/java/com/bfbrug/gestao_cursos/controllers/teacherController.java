package com.bfbrug.gestao_cursos.controllers;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.exceptions.NotFoundException;
import com.bfbrug.gestao_cursos.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class teacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@RequestBody @Valid TeacherDTO teacherDTO){
        var savedTeacher = teacherService.createTeacher(teacherDTO);

        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherDTO> getCourseById(@PathVariable("id") UUID teacherId){
        var teacherDTO = teacherService.getTeacherById(teacherId);

        return ResponseEntity.ok(teacherDTO);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){

        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("{id}")
    public ResponseEntity<TeacherDTO> updateTeacherById(
            @PathVariable("id") UUID teacherId,  @RequestBody @Valid TeacherDTO teacherDTO){
        var updateTeacherDTO = teacherService.updateTeacher(teacherId, teacherDTO);

        return ResponseEntity.ok(updateTeacherDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") UUID courseId) {

        teacherService.deleteTeacher(courseId);

        return ResponseEntity.ok("Course deleted successfully!");
    }
//
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<TeacherDTO> list(){
//        return teacherService.listTeacher();
//    }
//
//    @GetMapping("/{teacherId}")
//    @ResponseStatus(HttpStatus.OK)
//    public TeacherDTO getTeacherById(@PathVariable(value = "teacherId") UUID teacherId){
//        return teacherService.findTeacherById(teacherId).orElseThrow(NotFoundException::new);
//    }
//
//    @PutMapping("/{teacherId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<TeacherDTO> updatedTeacherById(@Validated @PathVariable("teacherId") UUID teacherId, @RequestBody TeacherDTO teacher) {
//
//        return teacherService.updateTeacherById(teacher, teacherId);
//    }
//
//    @PatchMapping("/{teacherId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<TeacherDTO> patchTeacherById(@Validated @PathVariable("teacherId") UUID teacherId,  @RequestBody Map<String, Object> fieldsTeacher) {
//
//        return teacherService.patchTeacherById(teacherId, fieldsTeacher);
//    }
//
//    @DeleteMapping("/{teacherId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<Object> deleteTeacherById(@PathVariable("teacherId") UUID teacherId) {
//
//        if(!teacherService.deleteTeacherById(teacherId)){
//            throw new NotFoundException();
//        }
//
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}
