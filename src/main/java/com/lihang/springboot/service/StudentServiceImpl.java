package com.lihang.springboot.service;

import com.lihang.springboot.entity.Student;
import com.lihang.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper mapper;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Student selectStudentById(int id) {
        return mapper.selectStudentById(id);
    }

    @Override
    public List<Student> selectAllStudent() {
        return mapper.selectAllStudent();
    }

    @Override
    public boolean updateStudentById(Student student) {
        if (mapper.selectStudentById(student.getId()) != null) {
            logger.log(Level.INFO, "更新不存在的数据项");
            return false;
        }
        mapper.updateStudent(student);
        return true;
    }

    @Override
    public void insertStudent(Student student) throws Exception {
        try {
            mapper.insertStudent(student);
        } catch (DataIntegrityViolationException e) {
            logger.log(Level.INFO, "插入重复");
            throw e;
        }
    }

    @Override
    public boolean deleteStudentById(int id) {
        if (mapper.selectStudentById(id) != null) {
            logger.log(Level.INFO, "删除不存在的数据项");
            return false;
        }
        mapper.deleteStudentById(id);
        return true;
    }

    public StudentServiceImpl(StudentMapper dao) {
        this.mapper = dao;
    }
}
