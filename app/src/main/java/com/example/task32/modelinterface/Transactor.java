package com.example.task32.modelinterface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.example.myapplication.model.Student;

import java.util.List;

@Dao
public abstract class Transactor {

    @Query("SELECT * FROM Student WHERE id=(SELECT MAX(id) FROM Student)")
   public abstract List<Student> getLast();

    @Update
    public abstract void update(Student student);


    @Transaction
    public void ChangeLast()
    {
        Student s=getLast().get(0);

        s.setSername("Иванов");
        s.setName("Иван");
        s.setSecondname("Иванович");

        //s.setFIO("Иванов Иван Иванович");
        update(s);
    }

}
