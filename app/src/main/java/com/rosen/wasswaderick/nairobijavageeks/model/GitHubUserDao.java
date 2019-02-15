package com.rosen.wasswaderick.nairobijavageeks.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Derick W on 13,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@Dao
public interface GitHubUserDao {

    @Insert
    void insert(JavaGeekGitHubUser word);

//    @Query("SELECT * from github_user ORDER BY username ASC")
//    LiveData<List<JavaGeekGitHubUser>> getAllGithubUsers();

}
