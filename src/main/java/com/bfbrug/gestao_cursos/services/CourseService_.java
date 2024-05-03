package com.bfbrug.gestao_cursos.services;

import com.bfbrug.gestao_cursos.dto.CourseDTO;
import com.bfbrug.gestao_cursos.mappers.CourseMapper;
import com.bfbrug.gestao_cursos.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService_ {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    public CourseDTO createCourse(CourseDTO courseDTO){

        return courseMapper.toDto(courseRepository
                .save(courseMapper.toCourse(courseDTO)));
    }

    public List<CourseDTO> listCourse(){
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> findCourseById(UUID CourseId){
        return Optional.ofNullable(courseMapper.toDto(
                courseRepository.findById(CourseId).orElse(null)));
    }

    public ResponseEntity<CourseDTO> updateCourseById(UUID courseId, CourseDTO courseDTO){

        return courseRepository.findById(courseId)
                .map(courseUpdate -> {
                    courseUpdate.setName(courseDTO.getName());
                    courseUpdate.setDescription(courseDTO.getDescription());
                    courseUpdate.setStartCourse(courseDTO.getStartCourse());
                    courseUpdate.setFinalCourse(courseDTO.getFinalCourse());

                    CourseDTO updated = courseMapper.toDto(courseRepository.save(courseUpdate));

                    return ResponseEntity.ok().body(updated);

                }).orElse(ResponseEntity.notFound().build());
    }

    public Boolean deleteCourseById(UUID courseId){

        if(courseRepository.existsById(courseId)){
            courseRepository.deleteById(courseId);

            return true;
        }

        return false;
    }

}
