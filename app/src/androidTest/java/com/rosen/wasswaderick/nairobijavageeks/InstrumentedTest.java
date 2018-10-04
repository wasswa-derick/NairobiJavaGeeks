package com.rosen.wasswaderick.nairobijavageeks;

import android.content.Context;
import android.os.Parcel;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rosen.wasswaderick.nairobijavageeks.model.GitHubUsers;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    User user;
    JavaGeekGitHubUser javaGeekGitHubUser;

    @Before
    public void setUp() {
         user = new User("test name", "company", "blog", "location", "bio", "23", "24", "111");
         javaGeekGitHubUser = new JavaGeekGitHubUser( "IMAGE", "USERNAME",  "COMPANY", "URL", "HTML URL", user);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.rosen.wasswaderick.nairobijavageeks", appContext.getPackageName());
    }

    @Test
    public void testUserParcelable(){

        User user2 = new User();
        user2.setName("Names");
        user2.setCompany("company");
        user2.setBio("company");
        user2.setLocation("company");
        user2.setBio("company");
        user2.setBlog("company");
        user2.setFollowers("43");
        user2.setFollowing("23");
        user2.setPublicRepos("32");

        Parcel parcel = Parcel.obtain();
        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);

        User createdFromParcel = User.CREATOR.createFromParcel(parcel);
        User [] newArray = User.CREATOR.newArray(1);
        assertThat(createdFromParcel.getName(), is("test name"));
        assertThat(createdFromParcel.getCompany(), is("company"));
        assertThat(createdFromParcel.getBlog(), is("blog"));
        assertEquals(newArray.length, 1);
    }

    @Test
    public void testJavaGeekGitHubUserParcelable(){

        JavaGeekGitHubUser javaGeekGitHubUser1 = new JavaGeekGitHubUser();
        javaGeekGitHubUser1.setImage("Data");
        javaGeekGitHubUser1.setHtmlUrl("Data");
        javaGeekGitHubUser1.setUrl("Data");
        javaGeekGitHubUser1.setCompany("Data");
        javaGeekGitHubUser1.setUsername("Data");
        javaGeekGitHubUser1.setDetails(user);

        Parcel parcel = Parcel.obtain();
        javaGeekGitHubUser.writeToParcel(parcel, javaGeekGitHubUser.describeContents());
        parcel.setDataPosition(0);

        JavaGeekGitHubUser createdFromParcel = JavaGeekGitHubUser.CREATOR.createFromParcel(parcel);
        JavaGeekGitHubUser [] newArray = JavaGeekGitHubUser.CREATOR.newArray(1);
        assertThat(createdFromParcel.getCompany(), is("COMPANY"));
        assertThat(createdFromParcel.getImage(), is("IMAGE"));
        assertThat(createdFromParcel.getUrl(), is("URL"));
        assertEquals(user, javaGeekGitHubUser1.getDetails());
        assertEquals(newArray.length, 1);
    }

    @Test
    public void testGitHubUsersParcelable(){

        ArrayList<JavaGeekGitHubUser> arrayList = new ArrayList<>();
        arrayList.add(javaGeekGitHubUser);
        GitHubUsers gitHubUsers = new GitHubUsers("111", arrayList);

        GitHubUsers users2 = new GitHubUsers();
        users2.setDevelopers("32");
        users2.setUsers(arrayList);

        assertThat(gitHubUsers.getDevelopers(), is("111"));
        assertThat(gitHubUsers.getUsers(), is(arrayList));
    }
}
