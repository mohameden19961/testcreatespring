package com.example.monprojet.services;

import com.example.monprojet.data.entities.Student;
import com.example.monprojet.data.repositories.StudentRepository;
import com.example.monprojet.dto.CourseDTO;
import com.example.monprojet.dto.StudentDTO;
import com.example.monprojet.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<StudentDTO> getAllStudentsPaginated(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'id : " + id));
        return convertToDTO(student);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'id : " + id));
        
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        
        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Étudiant non trouvé avec l'id : " + id);
        }
        studentRepository.deleteById(id);
    }

    public List<StudentDTO> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private StudentDTO convertToDTO(Student student) {
        List<CourseDTO> courseDTOs = null;
        if (student.getCourses() != null) {
            courseDTOs = student.getCourses().stream()
                    .map(course -> new CourseDTO(course.getId(), course.getTitle()))
                    .collect(Collectors.toList());
        }
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), courseDTOs);
    }

}
