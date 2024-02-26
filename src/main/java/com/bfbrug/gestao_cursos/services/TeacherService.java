package com.bfbrug.gestao_cursos.services;

import com.bfbrug.gestao_cursos.Records.TeacherRecord;
import com.bfbrug.gestao_cursos.dto.TeacherDTO;
import com.bfbrug.gestao_cursos.exceptions.TeacherFoundException;
import com.bfbrug.gestao_cursos.mappers.TeacherMapper;
import com.bfbrug.gestao_cursos.models.Teacher;
import com.bfbrug.gestao_cursos.repositories.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {

        teacherRepository.findByEmail(teacherDTO.getEmail())
                .ifPresent((teacher) -> {
                    throw new TeacherFoundException();
                });

        return teacherMapper.toDto(teacherRepository
                .save(teacherMapper.toTeacher(teacherDTO)));
    }


    public List<TeacherDTO> listTeacher(){
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<TeacherDTO> findTeacherById(UUID teacherId){
        return Optional.ofNullable(teacherMapper.toDto(
                teacherRepository.findById(teacherId).orElse(null)));

    }

    public ResponseEntity<TeacherDTO> updateTeacherById(TeacherDTO teacherDTO, UUID id){

        teacherRepository.findByEmail(teacherDTO.getEmail())
                .ifPresent((teacher) -> {
                    throw new TeacherFoundException();
                });

        return teacherRepository.findById(id)
                .map(teacherUpdate -> {
                    teacherUpdate.setName(teacherDTO.getName());
                    teacherUpdate.setEmail(teacherDTO.getEmail());
                    teacherUpdate.setPassword(teacherDTO.getPassword());

                    TeacherDTO updated = teacherMapper.toDto(teacherRepository.save(teacherUpdate));

                    return ResponseEntity.ok().body(updated);

                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<TeacherDTO> patchTeacherById(UUID teacherId, Map<String, Object> fields) {

        TeacherDTO teacherDTO = teacherMapper.toDto(teacherRepository.findById(teacherId).orElse(null));
        TeacherDTO existingTeacher = teacherDTO;

        mergePatchTeacher(fields, existingTeacher);

        TeacherDTO updated = teacherMapper.toDto(teacherRepository.save(teacherMapper.toTeacher(existingTeacher)));

        return ResponseEntity.ok().body(updated);

    }

    public void mergePatchTeacher(Map<String, Object> fields, TeacherDTO teacher){

        ObjectMapper objectMapper = new ObjectMapper();

        TeacherDTO teacherConvert = objectMapper.convertValue(fields, TeacherDTO.class);

        fields.forEach((propertyName, propertyValue) -> {

            if(propertyName.equals("email")) {
                teacherRepository.findByEmail((String) propertyValue)
                        .ifPresent((email) -> {
                            throw new TeacherFoundException();
                        });
            }

            Field field = ReflectionUtils.findField(TeacherDTO.class, propertyName);
            field.setAccessible(true);

            Object newValueTeacher = ReflectionUtils.getField(field, teacherConvert);

            ReflectionUtils.setField(field, teacher, newValueTeacher);
        });

    }

    public Boolean deleteTeacherById(UUID teacherId){

        if(teacherRepository.existsById(teacherId)){
            teacherRepository.deleteById(teacherId);

            return true;
        }

        return false;
    }
}
