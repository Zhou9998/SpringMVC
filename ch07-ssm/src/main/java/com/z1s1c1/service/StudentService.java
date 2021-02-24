package com.z1s1c1.service;

import com.z1s1c1.domain.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);
    List<Student> findStudent();
}
