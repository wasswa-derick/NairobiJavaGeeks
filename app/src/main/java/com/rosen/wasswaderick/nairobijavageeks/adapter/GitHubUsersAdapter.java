package com.rosen.wasswaderick.nairobijavageeks.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.databinding.DevelopRowGridBinding;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;

import java.util.ArrayList;

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
        DevelopRowGridBinding itemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.develop_row_grid, viewGroup, false);
        return new GitHubUsersViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubUsersViewHolder gitHubUsersViewHolder, int position) {

        JavaGeekGitHubUser javaGeekGitHubUser = javaGeekGitHubUserList.get(position);
        gitHubUsersViewHolder.bind(javaGeekGitHubUser);

//        String username = "@" + javaGeekGitHubUser.getUsername();
//        gitHubUsersViewHolder.username.setText(username);
//        gitHubUsersViewHolder.develop_url.setText(javaGeekGitHubUser.getHtmlUrl());
//
//        String imageUrl = javaGeekGitHubUser.getImage();
//
//        GlideApp
//                .with(context)
//                .asBitmap()
//                .load(imageUrl)
//                .placeholder(R.drawable.cat)
//                .centerCrop()
//                .into(gitHubUsersViewHolder.profileImage);

    }

    @Override
    public int getItemCount() {
        return javaGeekGitHubUserList.size();
    }

    public class GitHubUsersViewHolder extends RecyclerView.ViewHolder {

        private final DevelopRowGridBinding binding;

        public GitHubUsersViewHolder(@NonNull DevelopRowGridBinding developRowGridBinding) {
            super(developRowGridBinding.getRoot());
            this.binding = developRowGridBinding;
        }

        public void bind(JavaGeekGitHubUser javaGeekGitHubUser) {
            binding.setGithubuser(javaGeekGitHubUser);
            binding.executePendingBindings();
        }
    }

}
