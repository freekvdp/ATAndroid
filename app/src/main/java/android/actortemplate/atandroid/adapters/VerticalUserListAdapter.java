package android.actortemplate.atandroid.adapters;

import android.actortemplate.atandroid.R;
import android.actortemplate.atandroid.domain.Project;
import android.actortemplate.atandroid.domain.User;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlerkbook-pro on 09/04/2017.
 */

public class VerticalUserListAdapter extends
        FirebaseRecyclerAdapter<User, VerticalUserListAdapter.vUserViewHolder> {

    private List<String> userlist;

    public VerticalUserListAdapter(Project p, Boolean child) {
        super(User.class, R.layout.horizontal_user_item,
                vUserViewHolder.class,
                FirebaseDatabase.getInstance().getReference("users"));
        Log.d("LOG", "vUserList project: "+p.getName());
        if(child){
            this.userlist = p.getAnalists();
        } else {
            this.userlist = p.getMembers();
        }
    }

    protected void populateViewHolder(vUserViewHolder viewHolder,
                                      User user, int position) {
        Log.d("LOG",user.getName() + " " + user.getEmail());

        if(userlist.contains(user.getId())) {
            viewHolder.name.setText(user.getName());
//        viewHolder.personAvatar.setImageResource(R.drawable.no_photo);
            viewHolder.key = getRef(position).getKey();
        }
    }

    public static class vUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
//        public ImageView personAvatar;
        public String key;

        public vUserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.person_name);
//            personAvatar = (ImageView) view.findViewById(R.id.person_avatar);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            System.out.println("Person clicked");
//            Intent intent = new Intent(v.getContext(), ShowProjectContent.class);
//            intent.putExtra("key", key);
//            v.getContext().startActivity(intent);
        }
    }
}
