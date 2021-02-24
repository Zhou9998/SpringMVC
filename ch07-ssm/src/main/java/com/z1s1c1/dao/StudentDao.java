package com.z1s1c1.dao;

import com.z1s1c1.domain.Student;

import java.util.List;

public interface StudentDao {

    int insertStudent(Student student);
    List<Student> selectStudent();
}
