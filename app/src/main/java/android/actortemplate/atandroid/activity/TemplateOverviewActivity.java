package android.actortemplate.atandroid.activity;

import android.actortemplate.atandroid.adapters.ProjectsAdapter;
import android.actortemplate.atandroid.adapters.TemplateAdapter;
import android.actortemplate.atandroid.domain.Project;
import android.actortemplate.atandroid.domain.Template;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.actortemplate.atandroid.R;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TemplateOverviewActivity extends AppCompatActivity {

    private String key;
    private Boolean isAnalist;
    private RecyclerView templatescontainer;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private TemplateAdapter mAdapter;
    private List<Template> projectTemplates = new ArrayList<Template>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_overview);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        isAnalist = intent.getBooleanExtra("isAnalist", false);

        FloatingActionButton newTemplateButton = (FloatingActionButton) findViewById(R.id.new_template_button);
        newTemplateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TemplateOverviewActivity.this, ChangeTemplateActivity.class);
                i.putExtra("pkey", key);
                i.putExtra("tkey", "");
                startActivity(i);
            }
        });
        if(!isAnalist) {
            newTemplateButton.setVisibility(View.GONE);
        }


        //add a OnItemClickListener
        templatescontainer = (RecyclerView) findViewById(R.id.recycler_view_templates);

        mAdapter = new TemplateAdapter(isAnalist, key);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        templatescontainer.setLayoutManager(mLayoutManager);
        templatescontainer.setItemAnimator(new DefaultItemAnimator());
        templatescontainer.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
