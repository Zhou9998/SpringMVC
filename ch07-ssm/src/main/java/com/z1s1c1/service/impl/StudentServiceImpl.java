package com.z1s1c1.service.impl;

import com.z1s1c1.dao.StudentDao;
import com.z1s1c1.domain.Student;
import com.z1s1c1.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    //引用类型的自动注入
    @Resource
    private StudentDao studentDao;
    @Override
    public int addStudent(Student student) {
        int num = studentDao.insertStudent(student);
        return num;
    }

    @Override
    public List<Student> findStudent() {
        return studentDao.selectStudent();
    }
}
