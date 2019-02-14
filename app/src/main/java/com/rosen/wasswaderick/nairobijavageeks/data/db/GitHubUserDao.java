package com.rosen.wasswaderick.nairobijavageeks.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;


import java.util.List;

/**
 * Created by Derick W on 13,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@Dao
public interface GitHubUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(JavaGeekGitHubUser word);

    @Query("SELECT * from github_user ORDER BY username ASC")
    List<JavaGeekGitHubUser> getAllGithubUsers();

}
