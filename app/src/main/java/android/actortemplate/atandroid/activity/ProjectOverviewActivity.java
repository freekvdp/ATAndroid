package android.actortemplate.atandroid.activity;

import android.actortemplate.atandroid.adapters.ProjectsAdapter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.actortemplate.atandroid.R;

public class ProjectOverviewActivity extends AppCompatActivity {

    RecyclerView projectscontainer;
    ProjectsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // add a OnItemClickListener
        projectscontainer = (RecyclerView) findViewById(R.id.recycler_view_projects);

        SharedPreferences userInfo = getSharedPreferences("USERID", 0);
        mAdapter = new ProjectsAdapter(userInfo.getString("userId", "NotSignedIn"));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        projectscontainer.setLayoutManager(mLayoutManager);
        projectscontainer.setItemAnimator(new DefaultItemAnimator());
        projectscontainer.setAdapter(mAdapter);
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
