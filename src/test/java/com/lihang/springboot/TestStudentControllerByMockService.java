package com.lihang.springboot;

import com.lihang.springboot.controller.StudentController;
import com.lihang.springboot.entity.Student;
import com.lihang.springboot.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudentControllerByMockService {
    StudentService service;

    StudentController controller;

    // 测试数据
    ArrayList<Student> array;
    Student student;
    Student illegalStudent;
    int illegalId;

    @Before
    public void init() {
        service = mock(StudentService.class);

        array = new ArrayList();

        array.add(new Student(1, "lihang", 18, 1, "male", "ITDept"));
        array.add(new Student(2, "zhangsan", 20, 2, "female", "MathDept"));
        array.add(new Student(3, "lisi", 22, 3, "male", "EnglishDept"));

        student = new Student(3, "lisi", 22, 3, "male", "EnglishDept");

        illegalStudent = new Student(-10, "lisi", 22, 3, "male", "EnglishDept");

        illegalId = -100;
        when(service.selectAllStudent()).thenReturn(array);

        when(service.selectStudentById(anyInt()))
                .thenReturn(student);

        doNothing().when(service).deleteStudentById(anyInt());

        doNothing().when(service).updateStudentById(any(Student.class));

        Assertions.assertDoesNotThrow(() -> service.insertStudent(any(Student.class)));

        controller = new StudentController(service);
    }

    @Test
    public void testSelectAll() throws Exception {
        Assert.assertEquals(controller.selectAllStudent(), array);
    }

    @Test
    public void testSelectById() throws Exception {
        Assert.assertEquals(controller.selectStudentById(1), student);

        // 传参数不合法，返回空
        Assert.assertNull(controller.selectStudentById(-1));
    }

    @Test
    public void testUpdateById() throws Exception {
        Assertions.assertDoesNotThrow(() -> controller.updateStudent(student));

        Assertions.assertDoesNotThrow(() -> controller.updateStudent(illegalStudent));
    }

    @Test
    public void testDeleteById() throws Exception {
        Assertions.assertDoesNotThrow(() -> controller.deleteStudent(1));

        Assertions.assertDoesNotThrow(() -> controller.deleteStudent(illegalId));
    }

    @Test
    public void testInsert() throws Exception {
        //  测试正常插入
        Assert.assertTrue(controller.insertStudent(student));

        //  测试插入失败
        doThrow(Exception.class).when(service).insertStudent(student);

        Assert.assertFalse(controller.insertStudent(student));

        //  测试插入不合法
        Assertions.assertDoesNotThrow(() -> controller.insertStudent(illegalStudent));
    }
}