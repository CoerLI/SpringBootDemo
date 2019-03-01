package com.lihang.springboot.service;

import com.lihang.springboot.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    public Student selectStudentById(int id);

    public List<Student> selectAllStudent();

    public boolean updateStudentById(Student student);

    public void insertStudent(Student student) throws Exception;

    public boolean deleteStudentById(int id);
}
