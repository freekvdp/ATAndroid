package android.actortemplate.atandroid.adapters;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.activity.ShowPersonActivity;
import android.actortemplate.atandroid.activity.ShowUserActivity;
import android.actortemplate.atandroid.domain.Actor;
import android.actortemplate.atandroid.domain.Template;
import android.actortemplate.atandroid.domain.User;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Vlerkbook-pro on 05/04/2017.
 */

public class HorizontalPersonListAdapter extends FirebaseRecyclerAdapter<Actor, HorizontalPersonListAdapter.hPersonViewHolder> {

    private String template;

    public HorizontalPersonListAdapter() {
        super(Actor.class, R.layout.horizontal_user_item,
                hPersonViewHolder.class,
                FirebaseDatabase.getInstance().getReference("persons"));
    }
    public void setData(String templateid){
        this.template = templateid;
    }

    protected void populateViewHolder(hPersonViewHolder viewHolder,
                                      Actor actor, int position) {
        if(actor.getTemplateid().equals(template)) {
            viewHolder.name.setText(actor.getName());
            viewHolder.actorid = getRef(position).getKey();
            viewHolder.personAvatar.setImageResource(R.drawable.logo4_round);
            viewHolder.itemView.setTag(position);
        } else {
            viewHolder.container.setVisibility(View.GONE);
        }
    }

    public static class hPersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout container;
        public TextView name;
        public ImageView personAvatar;
        public String actorid;

        public hPersonViewHolder(View view) {
            super(view);
            container = (LinearLayout) view.findViewById(R.id.main_layout);
            name = (TextView) view.findViewById(R.id.person_name);
            personAvatar = (ImageView) view.findViewById(R.id.person_avatar);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ShowPersonActivity.class);
            intent.putExtra("actorid", actorid);
            v.getContext().startActivity(intent);
        }
    }
}
