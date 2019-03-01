package com.lihang.springboot;

import com.lihang.springboot.entity.Student;
import com.lihang.springboot.mapper.StudentMapper;
import com.lihang.springboot.service.StudentService;
import com.lihang.springboot.service.StudentServiceImpl;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudentServiceByMockDao {
    StudentService service;

    StudentMapper dao;

    ArrayList<Student> array;
    Student student;

    @Before
    public void init() {
        dao = mock(StudentMapper.class);

        array = new ArrayList<>();

        array.add(new Student(1, "lihang", 18, 1, "male", "ITDept"));
        array.add(new Student(2, "zhangsan", 20, 2, "female", "MathDept"));
        array.add(new Student(3, "lisi", 22, 3, "male", "EnglishDept"));

        when(dao.selectAllStudent()).thenReturn(array);

        // 默认能查到student
        when(dao.selectStudentById(anyInt())).thenReturn(student);

        doNothing().when(dao).deleteStudentById(anyInt());

        doNothing().when(dao).insertStudent(any(Student.class));

        doNothing().when(dao).updateStudent(any(Student.class));

        service = new StudentServiceImpl(dao);
    }

    @Test
    public void testSelectAll() {
        Assert.assertEquals(service.selectAllStudent(), array);
    }

    @Test
    public void testSelectById() {
        Assert.assertEquals(service.selectStudentById(1), student);
    }

    @Test
    public void testUpdateById() {
        Assertions.assertDoesNotThrow(() -> dao.updateStudent(student));
    }

    @Test
    public void testDeleteById() {
        Assertions.assertDoesNotThrow(() -> dao.deleteStudentById(1));
    }

    @Test
    public void testInsert() {
        // 测试正常插入student
        Assertions.assertDoesNotThrow(() -> dao.insertStudent(student));

        // 测试插入时已经存在student，抛出
        doThrow(new DataIntegrityViolationException("")).when(dao).insertStudent(any(Student.class));
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> dao.insertStudent(student));
    }
}
