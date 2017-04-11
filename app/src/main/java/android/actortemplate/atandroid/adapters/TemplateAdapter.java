package android.actortemplate.atandroid.adapters;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.activity.ChangeTemplateActivity;
import android.actortemplate.atandroid.domain.Actor;
import android.actortemplate.atandroid.domain.Template;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlerkbook-pro on 07/04/2017.
 */

public class TemplateAdapter extends
        FirebaseRecyclerAdapter<Template,
                TemplateAdapter.templateViewHolder> {

    private Boolean isAnalist;

    public TemplateAdapter(Boolean isAnalist, String projectid) {
        super(Template.class, R.layout.template_row,
                TemplateAdapter.templateViewHolder.class,
                FirebaseDatabase.getInstance().getReference("templates")
                        .orderByChild("projectkey").equalTo(projectid));
        this.isAnalist = isAnalist;
    }

    @Override
    protected void populateViewHolder(TemplateAdapter.templateViewHolder viewHolder,
                                      Template template, int position) {
        viewHolder.templatekey = getRef(position).getKey();
        viewHolder.projectkey = template.getProjectkey();
        viewHolder.title.setText(template.getRolename());
        viewHolder.description.setText(template.getDescription());
        viewHolder.aActors.setData(getRef(position).getKey());

        // verstopt de buttons als de ingelogde gebruiker geen analist is.
        if (!isAnalist) {
            viewHolder.editbutton.setVisibility(View.GONE);
            viewHolder.deletebutton.setVisibility(View.GONE);
        }
    }


    public static class templateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, description;
        public String projectkey;
        public String templatekey;
        public Button editbutton, deletebutton;
        public RecyclerView rActors;
        public HorizontalPersonListAdapter aActors;
        private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("templates");

        public templateViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();

            title = (TextView) view.findViewById(R.id.template_title);
            description = (TextView) view.findViewById(R.id.template_desc);

            editbutton = (Button) view.findViewById(R.id.template_edit_button);
            deletebutton = (Button) view.findViewById(R.id.template_delete_button);

            //set de personen in de horizontale recyclerview
            aActors = new HorizontalPersonListAdapter();
            rActors = (RecyclerView) view.findViewById(R.id.recycler_view_members);
            rActors.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            rActors.setAdapter(aActors);

            //link naar change template pagina
            editbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ChangeTemplateActivity.class);
                    i.putExtra("pkey", projectkey);
                    i.putExtra("tkey", templatekey);
                    view.getContext().startActivity(i);
                }
            });
            deletebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //delete template from database
                    mDatabase.child(templatekey).removeValue();
                }
            });

        }

        @Override
        public void onClick(View v) {
            Log.d("LOG", "Template Clicked");
//            Intent intent = new Intent(v.getContext(), TemplateOverviewActivity.class);
//            intent.putExtra("key", key);
//            intent.putExtra("isAnalist", isAnalist);
//            v.getContext().startActivity(intent);
        }
    }
}