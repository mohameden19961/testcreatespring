package com.example.monprojet.data.repositories;

import com.example.monprojet.data.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
