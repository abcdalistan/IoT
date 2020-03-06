package com.example.posturedetector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseReference dref;
    FirebaseDatabase db;
    TextView posture, position;
    String angle, status;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        listView = (ListView) findViewById(R.id.listview);
        posture = (TextView) findViewById(R.id.angle);
        position = (TextView) findViewById(R.id.status);
        Button delete = (Button) findViewById(R.id.delete);
        Button save = (Button) findViewById(R.id.save);
        dref = db.getInstance().getReference("Values");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.child("Angle").getValue().toString();
                String value = dataSnapshot.child("Status").getValue().toString();
                posture.setText(data);
                position.setText(value);
                arrayList.add("Angle: " + data + " degrees" + "     Status: " + value);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*values.setAngle(posture.getText().toString());
              values.setStatus(position.getText().toString());
              dref.child(String.valueOf(maxid+1)).setValue(values);*/
                String val = position.getText().toString();
                float value = Float.parseFloat(posture.getText().toString());
                String key = dref.push().getKey();
                dref.child(key).child("Angle").setValue(value);
                dref.child(key).child("Status").setValue(val);


            }
        });
    }
}

     /*   dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String string = dataSnapshot.getValue(String.class);
                arrayList.add(string);
                arrayAdapter.notifyDataSetChanged();
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
}*/
       /* dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();

                    Log.d(TAG, "onDataChange: Angle: " + data.get("Angle"));
                    Log.d(TAG, "onDataChange: Status: " + data.get("Status"));
                    Log.d(TAG, "onDataChange: Key: " + snapshot.getKey());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onFailure: " + databaseError.getMessage());
            }

        });
//


        }
    }
*/


