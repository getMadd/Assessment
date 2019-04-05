package geniusplaza.assessment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import geniusplaza.assessment.R;
import geniusplaza.assessment.listusers.UserListActivity;
import geniusplaza.assessment.models.User;

/**
 * Created by jamarkushodge on 4/3/19.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder>{

    private List<User> dataList;
    private UserListActivity context;

        public UsersAdapter(UserListActivity context, List<User> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        private TextView first_name;
        private TextView last_name;
        private ImageView image_view;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            first_name = mView.findViewById(R.id.userFirstTV);
            last_name = mView.findViewById(R.id.userLastTV);
            image_view = mView.findViewById(R.id.userIV);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_users, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.first_name.setText(dataList.get(position).getFirst_name());
        holder.last_name.setText(dataList.get(position).getLast_name());
        // Load Image here
        Glide.with(holder.itemView)
                .load(dataList.get(position).getAvatar())
                .into(holder.image_view);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
