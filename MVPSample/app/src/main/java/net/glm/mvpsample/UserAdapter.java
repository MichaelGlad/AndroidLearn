package net.glm.mvpsample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 04/08/2017.
 */

public class UserAdapter extends RecyclerView.Adapter < UserAdapter.UserHolder> {

    static final String MY_LOGS = "myLogs";
    List<User> usersData = new ArrayList<>();

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                     .from(parent.getContext())
                     .inflate(R.layout.user_item,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(usersData.get(position));

    }

    public  void setUsersData( List<User> users){
        usersData.clear();
        usersData.addAll(users);
        notifyDataSetChanged();
        Log.d(MY_LOGS," Size of list is - "+ getItemCount());
    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }

    static class UserHolder extends RecyclerView.ViewHolder{

        TextView text;

        public  UserHolder(View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.userText);

        }
        void bind (User user){
            text.setText(String.format("id: %s, name: %s, email: %s", user.getId(), user.getName(), user.getEmail()));
        }

    }
}
