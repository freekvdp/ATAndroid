package android.actortemplate.atandroid.adapters;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.activity.TemplateOverviewActivity;
import android.actortemplate.atandroid.domain.Project;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlerkbook-pro on 06/04/2017.
 */

public class ProjectsAdapter extends 
    FirebaseRecyclerAdapter<Project, ProjectsAdapter.projectViewHolder> {

    private static final String TAG = "LOG";
    private String loggedinUser_id;

    public ProjectsAdapter(String analistid) {
        super(Project.class, R.layout.project_row,
                projectViewHolder.class,
                FirebaseDatabase.getInstance().getReference("projects")
        );
        loggedinUser_id = analistid;
    }

    @Override
    protected void populateViewHolder(projectViewHolder viewHolder, Project project, int position) {
        Log.d("LOG", project.toString());

        viewHolder.key = getRef(position).getKey();
        viewHolder.title.setText(project.getName());
        viewHolder.aAnalists.setData(project.getAnalists());
        viewHolder.aMembers.setData(project.getMembers());

        //verstopt de editbutton als de gebruiker geen analist is
        if (!project.getAnalists().contains(loggedinUser_id)) {
            viewHolder.editbutton.setVisibility(View.GONE);
            viewHolder.isAnalist = false;
        } else {
            viewHolder.isAnalist = true;
        }
    }

    public static class projectViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public String key;
        public Button editbutton;
        public Boolean isAnalist;
        public RecyclerView rAnalists, rMembers;
        public HorizontalUserListAdapter aAnalists, aMembers;

        public projectViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            title = (TextView) view.findViewById(R.id.project_title);

            //set de users in de horizontale recyclerviewers analisten en teamleden
            aAnalists = new HorizontalUserListAdapter();
            aMembers = new HorizontalUserListAdapter();
            rAnalists = (RecyclerView) view.findViewById(R.id.recycler_view_analists);
            rMembers = (RecyclerView) view.findViewById(R.id.recycler_view_members);

            rAnalists.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            rAnalists.setAdapter(aAnalists);

            rMembers.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            rMembers.setAdapter(aMembers);

            editbutton = (Button) view.findViewById(R.id.project_edit_button);
            Button projectDetails = (Button) view.findViewById(R.id.project_details_button);

            projectDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("LOG", "Project details Clicked");
                    Intent intent = new Intent(view.getContext(), TemplateOverviewActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("isAnalist", isAnalist);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
