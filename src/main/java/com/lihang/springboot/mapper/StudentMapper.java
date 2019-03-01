package com.lihang.springboot.mapper;

import com.lihang.springboot.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectStudentById(int id);

    @Select("SELECT * FROM student")
    List<Student> selectAllStudent();

    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteStudentById(int id);

    @Update("UPDATE student SET " +
            "name = #{name}, " +
            "age = #{age}, " +
            "classId = #{classId}, " +
            "sex = #{sex}, " +
            "dept = #{dept}" +
            " WHERE id=#{id}")
    void updateStudent(Student student);

    @Insert("INSERT INTO student values(#{id},#{name},#{age},#{classId},#{sex},#{dept})")
    void insertStudent(Student student);
}
