package android.actortemplate.atandroid.activity;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.domain.Actor;
import android.actortemplate.atandroid.domain.User;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
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

public class ShowUserActivity extends AppCompatActivity {

    private String userid;
    private ImageView avatar;
    private TextView personName, personFunction, personEmail, personNotes;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance()
            .getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        Intent i = getIntent();
        userid = i.getStringExtra("userid");

        avatar = (ImageView) findViewById(R.id.photoView);
        personName = (TextView) findViewById(R.id.nameView);
        personFunction = (TextView) findViewById(R.id.functionView);
        personEmail = (TextView) findViewById(R.id.emailView);
        personNotes = (TextView) findViewById(R.id.notesView);
        Log.d("LOG", "356478976534578    "+userid);

        mDatabase.child(userid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        avatar.setImageResource(R.drawable.logo4_round);
                        personName.setText(user.getName() + " " + user.getSurName());
                        personFunction.setText(user.getPosition());
                        personEmail.setText(user.getEmail());
                        personNotes.setText(user.getAnnotation());
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
