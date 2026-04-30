package com.example.monprojet.services;

import com.example.monprojet.data.entities.Course;
import com.example.monprojet.data.repositories.CourseRepository;
import com.example.monprojet.dto.CourseDTO;
import com.example.monprojet.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec l'id : " + id));
        return convertToDTO(course);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec l'id : " + id));
        course.setTitle(courseDTO.getTitle());
        Course updatedCourse = courseRepository.save(course);
        return convertToDTO(updatedCourse);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cours non trouvé avec l'id : " + id);
        }
        courseRepository.deleteById(id);
    }

    private CourseDTO convertToDTO(Course course) {
        return new CourseDTO(course.getId(), course.getTitle());
    }
}
