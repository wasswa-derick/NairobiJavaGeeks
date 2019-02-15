package com.rosen.wasswaderick.nairobijavageeks.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;

/**
 * Created by Derick W on 13,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@Database(entities = JavaGeekGitHubUser.class, version = 1, exportSchema = false)
public abstract class GitHubUserDatabase extends RoomDatabase {

    public abstract GitHubUserDao gitHubUserDao();

    private static volatile GitHubUserDatabase INSTANCE;

    public static GitHubUserDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GitHubUserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GitHubUserDatabase.class,
                            "github_users")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
