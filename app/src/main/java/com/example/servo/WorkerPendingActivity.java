package com.example.servo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.servo.Adapter.WorkerPendingRecycViewAdapter;
import com.example.servo.Models.NewComplaint;

import java.util.ArrayList;

public class WorkerPendingActivity extends AppCompatActivity {

    public static ArrayList<Integer> time = new ArrayList<>();
    public static ArrayList<Integer> date = new ArrayList<>();
    public static ArrayList<String> uid = new ArrayList<>();
    public static ArrayList<String> phone = new ArrayList<>();
    public static ArrayList<Integer> room = new ArrayList<>();

    private ArrayList<NewComplaint> complaints;
//    private ArrayList<newComplaint> completedComplaints;

    private RecyclerView recyclerView;
    private WorkerPendingRecycViewAdapter workerPendingRecycViewAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_pending);

        recyclerView = findViewById(R.id.workerPendingView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        time.add(2300);
        time.add(2340);
        time.add(2200);

        date.add(25122022);
        date.add(25122022);
        date.add(25122022);

        uid.add("2565");
        uid.add("2545");
        uid.add("2625");

        phone.add("9082866493");
        phone.add("9082866493");
        phone.add("9082866493");

        room.add(1232);
        room.add(5401);
        room.add(5402);

        complaints = new ArrayList<NewComplaint>();

//        for (int i=0; i<6; i++)
//        {
//            NewComplaint currComp = new NewComplaint(time.get(i%3),date.get(i%3),"Electrician",uid.get(i%3),"asdf","sadfa",2534,515,"ak jdfnksd fiu","957425774","576541353",1800, 23102022);
//            complaints.add(currComp);
//        }

        workerPendingRecycViewAdapter = new WorkerPendingRecycViewAdapter(WorkerPendingActivity.this, complaints);
        recyclerView.setAdapter(workerPendingRecycViewAdapter);






    }
}