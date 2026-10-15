package com.Student.Management.System.repository;

import com.Student.Management.System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
