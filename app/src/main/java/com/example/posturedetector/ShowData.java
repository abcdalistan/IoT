package com.example.posturedetector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ShowData extends AppCompatActivity {
    ListView history;
    DatabaseReference ref;
    FirebaseDatabase db;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        db = FirebaseDatabase.getInstance();
        history = (ListView) findViewById(R.id.alldata);
        ref = db.getInstance().getReference("Values");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        history.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();
        final long currentDate= System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
        final String dataString = simpleDateFormat.format(currentDate);
        ref.child("Date").setValue(dataString);


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayList.add("Angle: " + dataSnapshot.child("Angle").getValue() + " Status: " + dataSnapshot.child("Status").getValue() + " Time/Date: " + dataString);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
