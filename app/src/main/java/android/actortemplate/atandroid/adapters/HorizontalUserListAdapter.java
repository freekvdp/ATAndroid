package android.actortemplate.atandroid.adapters;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.activity.ShowPersonActivity;
import android.actortemplate.atandroid.activity.ShowUserActivity;
import android.actortemplate.atandroid.domain.User;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Vlerkbook-pro on 05/04/2017.
 */

public class HorizontalUserListAdapter extends FirebaseRecyclerAdapter<User, HorizontalUserListAdapter.hUserViewHolder>  {

    private List<String> useridlist;

    public HorizontalUserListAdapter() {
        super(User.class, R.layout.horizontal_user_item,
                hUserViewHolder.class,
                FirebaseDatabase.getInstance().getReference("users"));
    }

    public void setData(List<String> users){
        this.useridlist = users;
    }

    protected void populateViewHolder(hUserViewHolder viewHolder,
                                      User user, int position) {
        if(useridlist.contains(user.getId())){
            viewHolder.name.setText(user.getName());
            viewHolder.userid = user.getId();
            viewHolder.personAvatar.setImageResource(R.drawable.logo4_round);
            viewHolder.itemView.setTag(position);
        } else {
            viewHolder.container.setVisibility(View.GONE);
        }
    }

    public static class hUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout container;
        public TextView name;
        public ImageView personAvatar;
        public String userid;

        public hUserViewHolder(View view) {
            super(view);
            container = (LinearLayout) view.findViewById(R.id.main_layout);
            name = (TextView) view.findViewById(R.id.person_name);
            personAvatar = (ImageView) view.findViewById(R.id.person_avatar);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ShowUserActivity.class);
            intent.putExtra("userid", userid);
            v.getContext().startActivity(intent);
        }
    }
}
