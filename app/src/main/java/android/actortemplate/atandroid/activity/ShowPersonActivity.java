package android.actortemplate.atandroid.activity;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.domain.Actor;
import android.actortemplate.atandroid.domain.Template;
import android.actortemplate.atandroid.domain.User;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Vlerkbook-pro on 11/04/2017.
 */

public class ShowPersonActivity extends AppCompatActivity {

    private String personid;
    private ImageView avatar;
    private TextView personName, personFunction, personEmail, personPhone, personNotes;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference dataReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        Intent i = getIntent();
        personid = i.getStringExtra("personid");

        dataReference = mDatabase.getReference("persons").child(personid);

        avatar = (ImageView) findViewById(R.id.photoView);
        personName = (TextView) findViewById(R.id.nameView);
        personFunction = (TextView) findViewById(R.id.functionView);
        personEmail = (TextView) findViewById(R.id.emailView);
        personPhone = (TextView) findViewById(R.id.phonenumberView);
        personNotes = (TextView) findViewById(R.id.notesView);

        dataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Actor actor = dataSnapshot.getValue(Actor.class);
                avatar.setImageResource(R.drawable.logo4_round);
                personName.setText(actor.getName());
                personFunction.setText(actor.getPosition());
                personEmail.setText(actor.getEmail());
                personPhone.setText(actor.getPhone());
                personNotes.setText(actor.getNotes());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOG", "ERROR");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }
}
