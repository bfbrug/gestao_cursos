package com.bfbrug.gestao_cursos.services;

import com.bfbrug.gestao_cursos.dto.TeacherDTO;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(UUID teacherId);

    List<TeacherDTO> getAllTeachers();

    TeacherDTO updateTeacher(UUID teacherId, TeacherDTO teacherDTO);

    void deleteTeacher(UUID teacherId);
}
