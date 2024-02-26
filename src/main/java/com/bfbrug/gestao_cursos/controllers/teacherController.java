package com.bfbrug.gestao_cursos.controllers;

import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.exceptions.NotFoundException;
import com.bfbrug.gestao_cursos.services.TeacherService;
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
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO create(@Validated @RequestBody TeacherDTO teacherDTO){
        return teacherService.createTeacher(teacherDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDTO> list(){
        return teacherService.listTeacher();
    }

    @GetMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO getTeacherById(@PathVariable(value = "teacherId") UUID teacherId){
        return teacherService.findTeacherById(teacherId).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherDTO> updatedTeacherById(@Validated @PathVariable("teacherId") UUID teacherId, @RequestBody TeacherDTO teacher) {

        return teacherService.updateTeacherById(teacher, teacherId);
    }

    @PatchMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherDTO> patchTeacherById(@Validated @PathVariable("teacherId") UUID teacherId,  @RequestBody Map<String, Object> fieldsTeacher) {

        return teacherService.patchTeacherById(teacherId, fieldsTeacher);
    }

    @DeleteMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTeacherById(@PathVariable("teacherId") UUID teacherId) {

        if(!teacherService.deleteTeacherById(teacherId)){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
