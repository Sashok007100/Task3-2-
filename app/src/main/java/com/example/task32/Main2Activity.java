package com.example.task32;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Asyn2Task as;

    public int getEvent_Diskription() {
        return Event_Diskription;
    }

    public void setEvent_Diskription(int event_Diskription) {
        Event_Diskription = event_Diskription;
    }

    private int Event_Diskription=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void InsData(View view)
    {
        this.setEvent_Diskription(2);
        as=new Asyn2Task();
        as.execute(this);
    }

    public void SelectData(View view)
    {
        this.setEvent_Diskription(1);
        as=new Asyn2Task();
        as.execute(this);
    }

    public void ChangeLast(View view)
    {
        this.setEvent_Diskription(3);
        as=new Asyn2Task();
        as.execute(this);

    }

    public void finisher(List<Student> list)
    {
            Intent intent = new Intent(this, Main3Activity.class);
            intent.putExtra("list", (ArrayList) list);
            startActivity(intent);
    }
}
