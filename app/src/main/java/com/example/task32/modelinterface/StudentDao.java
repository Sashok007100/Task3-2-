package com.example.task32.modelinterface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myapplication.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insertALL(Student... students);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Query("DELETE FROM Student")
    void deleteAllStudents();


}
