package com.rosen.wasswaderick.nairobijavageeks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Derick W on 25,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class GitHubUsersAdapter extends RecyclerView.Adapter<GitHubUsersAdapter.GitHubUsersViewHolder> {

    private Context context;
    private ArrayList<JavaGeekGitHubUser> javaGeekGitHubUserList;
    private LayoutInflater layoutInflater;

    public GitHubUsersAdapter(Context context, ArrayList<JavaGeekGitHubUser> javaGeekGitHubUserList){
        this.context = context;
        this.javaGeekGitHubUserList = javaGeekGitHubUserList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GitHubUsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GitHubUsersViewHolder(layoutInflater.inflate(R.layout.develop_row_grid, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubUsersViewHolder gitHubUsersViewHolder, int position) {

        JavaGeekGitHubUser javaGeekGitHubUser = javaGeekGitHubUserList.get(position);

        String username = "@" + javaGeekGitHubUser.getUsername();
        gitHubUsersViewHolder.username.setText(username);
        gitHubUsersViewHolder.develop_url.setText(javaGeekGitHubUser.getHtmlUrl());

        String imageUrl = javaGeekGitHubUser.getImage();

        GlideApp
                .with(context)
                .asBitmap()
                .load(imageUrl)
                .placeholder(R.drawable.cat)
                .centerCrop()
                .into(gitHubUsersViewHolder.profileImage);

    }

    @Override
    public int getItemCount() {
        return javaGeekGitHubUserList.size();
    }

    public class GitHubUsersViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView username;
        TextView develop_url;

        public GitHubUsersViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile);
            username = itemView.findViewById(R.id.developer_name);
            develop_url = itemView.findViewById(R.id.develop_url);
        }
    }

}
