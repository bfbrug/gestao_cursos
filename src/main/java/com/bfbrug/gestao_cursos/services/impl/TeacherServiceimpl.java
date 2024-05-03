package com.bfbrug.gestao_cursos.services.impl;

import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.exceptions.TeacherFoundException;
import com.bfbrug.gestao_cursos.mappers.TeacherMapper;
import com.bfbrug.gestao_cursos.models.Teacher;
import com.bfbrug.gestao_cursos.repositories.TeacherRepository;
import com.bfbrug.gestao_cursos.services.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherServiceimpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {

        teacherRepository.findByEmail(teacherDTO.getEmail())
                .ifPresent((teacher) -> {
                    throw new TeacherFoundException();
                });

        var teacher = teacherMapper.toTeacher(teacherDTO);
        var savedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toDto(savedTeacher);
    }

    @Override
    public TeacherDTO getTeacherById(UUID teacherId) {

        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found."));

        return teacherMapper.toDto(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(UUID teacherId, TeacherDTO teacherDTO) {

       Optional<Teacher> updateTeacher = teacherRepository.findById(teacherId);

       if(updateTeacher.isEmpty()){
           throw new RuntimeException("Teacher not found.");
       }

        teacherRepository.findByEmail(teacherDTO.getEmail())
                .ifPresent((foundTeacher) -> {
                    throw new TeacherFoundException();
                });

        BeanUtils.copyProperties(teacherDTO, updateTeacher.get());

        return teacherMapper.toDto(teacherRepository.save(updateTeacher.get()));
    }

    @Override
    public void deleteTeacher(UUID teacherId) {
        var updateTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found."));

        teacherRepository.deleteById(teacherId);
    }
}
