package geniusplaza.assessment.listusers;

import android.content.Context;

import geniusplaza.assessment.models.Create;
import geniusplaza.assessment.models.User;

/**
 * Created by jamarkushodge on 4/4/19.
 */

public interface UserListContract {

    interface View{
        void showUpdatedList();
        void handleFABClicked();
        void handAddUserIVClicked();
        void triggerDialogCardView(Boolean trigger);
        void triggerFABView(Boolean trigger);

    }


    interface Presenter{
        void initialize(Context context);
        User[] handleRefreshList();
        void handleAddUser(Create user);
        User[] getUsers();
        void saveUsersSharedPrefs(Context context, User[] users);
    }
}
