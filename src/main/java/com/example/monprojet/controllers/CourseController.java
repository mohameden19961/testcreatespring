package com.example.monprojet.controllers;

import com.example.monprojet.data.entities.Course;
import com.example.monprojet.data.repositories.CourseRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.CourseDTO;
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

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll().stream()
                .map(course -> new CourseDTO(course.getId(), course.getTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>("Liste des cours récupérée", true, courses));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseDTO>> createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok(new ApiResponse<>("Cours créé avec succès", true, 
                new CourseDTO(savedCourse.getId(), savedCourse.getTitle())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("Cours supprimé avec succès", true, null));
    }
}
