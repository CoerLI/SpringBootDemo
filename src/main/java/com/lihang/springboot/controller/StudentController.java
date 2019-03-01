package com.lihang.springboot.controller;

import com.lihang.springboot.entity.Student;
import com.lihang.springboot.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    @Autowired
    StudentService service;

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student selectStudentById(@PathVariable int id) {
        return service.selectStudentById(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> selectAllStudent() {
        return service.selectAllStudent();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public boolean insertStudent(@RequestBody Student student) {
        // 检查边界，禁止插入
        if (student.getId() <= 0) {
            return false;
        }
        try {
            service.insertStudent(student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Student student) {
        service.updateStudentById(student);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable int id) {
        service.deleteStudentById(id);
    }
}