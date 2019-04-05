package geniusplaza.assessment.listusers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import geniusplaza.assessment.TinyDB;
import geniusplaza.assessment.adapters.UsersAdapter;
import geniusplaza.assessment.models.Create;
import geniusplaza.assessment.models.User;
import geniusplaza.assessment.R;


public class UserListActivity extends AppCompatActivity implements UserListContract.View {

    private final int MAX_RETURN_USERS = 12;
    UserListPresenter presenter;
    RecyclerView usersListRecycler;
    UsersAdapter adapter;
    TextView textView;
    FloatingActionButton addUserFAB;
    CardView dialogCard;
    TextView inputName, inputJob;
    int clickCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersListRecycler = findViewById(R.id.recyclerView);
        addUserFAB = findViewById(R.id.addUserFAB);
        dialogCard = findViewById(R.id.dialogCV);

        presenter = new UserListPresenter();
        textView = findViewById(R.id.titleTV);
        textView.setText(R.string.title_text);
        inputJob = findViewById(R.id.inputJobET);
        inputName = findViewById(R.id.inputNameET);



        new getUsers().execute();
        showUpdatedList();
        handleFABClicked();
    }

    @Override
    public void showUpdatedList() {
        TinyDB tinydb = new TinyDB(this);
        ArrayList<User> users = tinydb.getListObject("usersList", User.class);

        adapter = new UsersAdapter(this,users);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        usersListRecycler.setLayoutManager(llm);
        usersListRecycler.setAdapter(adapter);

    }

    @Override
    public void handleFABClicked() {
        addUserFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCount++;
                if(clickCount == 2 && !inputName.getText().equals(" ") && !inputJob.getText().equals(" ") ){
                    handAddUserIVClicked();
                    triggerDialogCardView(false);
                    clickCount = 0;
                }
                else if (clickCount == 1){
                    triggerDialogCardView(true);
                }
            }

        });
    }

    @Override
    public void handAddUserIVClicked() {

        if(inputName.getText().toString() != null && inputJob.getText().toString() != null){
            Create create = new Create(inputName.getText().toString(),inputJob.getText().toString());
            presenter.handleAddUser(create);
        }

    }

    @Override
    public void triggerDialogCardView(Boolean trigger) {
        if(trigger){
            dialogCard.setVisibility(View.VISIBLE);
        }else dialogCard.setVisibility(View.INVISIBLE);
    }

    @Override
    public void triggerFABView(Boolean trigger) {
        if(trigger){
            addUserFAB.setVisibility(View.VISIBLE);
        }
        else addUserFAB.setVisibility(View.INVISIBLE);
    }

    public class getUsers extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            presenter.initialize(getApplicationContext());
            presenter.handleRefreshList();
            return null;
        }
    }

    private User[] initializeDummyUserArr(){
        User[] users = new User[MAX_RETURN_USERS];

        for(User user:users){
            user = new User();
        }
        return users;
    }


}