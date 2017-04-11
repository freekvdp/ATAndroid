package android.actortemplate.atandroid.activity;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.domain.Template;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * Created by Vlerkbook-pro on 08/04/2017.
 */

public class ChangeTemplateActivity extends AppCompatActivity {
    TextView changeTemplateTitle;
    EditText templateRolename, templateDescription;
    private String projectkey, templatekey;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("templates");
    private Template template;
    private String title = "New Template";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_template);

        Intent intent = getIntent();
        projectkey = intent.getStringExtra("pkey");
        templatekey = intent.getStringExtra("tkey");

        changeTemplateTitle = (TextView) findViewById(R.id.change_template_title);
        templateRolename = (EditText) findViewById(R.id.template_change_rolename);
        templateDescription = (EditText) findViewById(R.id.template_change_desc);

        if(!templatekey.equals("")) {
            mDatabase.child(templatekey)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            template = dataSnapshot.getValue(Template.class);
                            templateRolename.setText(template.getRolename());
                            templateDescription.setText(template.getDescription());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("LOG", "ERROR");
                        }
                    });

            title = "Edit Template";
        }

        changeTemplateTitle.setText(title);

        Button saveTemplate = (Button) findViewById(R.id.template_save_button);
        saveTemplate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Template nwTemplate = new Template();
                String rolename = templateRolename.getText().toString();
                String description = templateDescription.getText().toString();
                nwTemplate.setProjectkey(projectkey);
                nwTemplate.setRolename(rolename);
                nwTemplate.setDescription(description);

                Log.d("LOG", "Template bestaat: " + (template != null));
                //save new template to database
                if (template != null) {
                    mDatabase.child(templatekey).child("rolename").setValue(rolename);
                    mDatabase.child(templatekey).child("description").setValue(description);
                } else {
                    mDatabase.push().setValue(nwTemplate);
                }


                Intent intent = new Intent(view.getContext(), TemplateOverviewActivity.class);
                intent.putExtra("key", projectkey);
                intent.putExtra("isAnalist", true);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d("LOG", "onBackPressed Called");
        Intent i = new Intent(this.getApplicationContext(), ProjectOverviewActivity.class);
        i.putExtra("key", projectkey);
        i.putExtra("isAnalist", true);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }
}
