package com.example.posturedetector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    Value value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        db = FirebaseDatabase.getInstance();
        history = (ListView) findViewById(R.id.alldata);
        ref = db.getInstance().getReference().child("Values");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        history.setAdapter(adapter);
        value = new Value();
        Calendar calendar = Calendar.getInstance();
        final long currentDate= System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
        final String dataString = simpleDateFormat.format(currentDate);
        value.setTimedate(dataString);
        ref.child(value.getTimedate()).setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ShowData.this, "Successfully recorded!", Toast.LENGTH_LONG).show();
                    arrayList.add(value.getAngle());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ShowData.this, "Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}