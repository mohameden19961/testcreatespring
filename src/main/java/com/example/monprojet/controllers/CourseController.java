package com.example.monprojet.controllers;

import com.example.monprojet.data.entities.Course;
import com.example.monprojet.data.entities.Student;
import com.example.monprojet.data.repositories.CourseRepository;
import com.example.monprojet.data.repositories.StudentRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.CourseDTO;
import com.example.monprojet.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll().stream()
                .map(course -> new CourseDTO(course.getId(), course.getTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>("Liste des cours récupérée", true, courses));
    }

    @PostMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<CourseDTO>> addCourseToStudent(
            @PathVariable Long studentId,
            @RequestBody CourseDTO courseDTO) {
        
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé"));
        
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setStudent(student);
        
        Course savedCourse = courseRepository.save(course);
        
        return ResponseEntity.ok(new ApiResponse<>("Cours ajouté à l'étudiant", true, 
                new CourseDTO(savedCourse.getId(), savedCourse.getTitle())));
    }
}
