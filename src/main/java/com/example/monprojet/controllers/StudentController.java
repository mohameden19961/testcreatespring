package com.example.monprojet.controllers;

import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.StudentDTO;
import com.example.monprojet.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse<>("Liste des étudiants récupérée", true, students));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<StudentDTO>>> getStudentsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<StudentDTO> studentPage = studentService.getAllStudentsPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("Page d'étudiants récupérée", true, studentPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(new ApiResponse<>("Étudiant trouvé", true, student));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(new ApiResponse<>("Étudiant créé avec succès", true, createdStudent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(new ApiResponse<>("Étudiant mis à jour avec succès", true, updatedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse<>("Étudiant supprimé avec succès", true, null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> searchStudents(@RequestParam String name) {
        List<StudentDTO> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(new ApiResponse<>("Résultats de la recherche", true, students));
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<ApiResponse<StudentDTO>> enrollInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        StudentDTO student = studentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok(new ApiResponse<>("Inscription réussie", true, student));
    }
}
