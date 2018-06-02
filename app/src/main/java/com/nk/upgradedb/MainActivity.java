package com.nk.upgradedb;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.userDataRecyclerView)
    RecyclerView userDataRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ArrayList<User> userArrayList;
    private UserAdapter adapter;
    private AppDb db;
    private int RC_ADD_USER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }
    private void init() {
        db = new AppDb(this);

        userDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userDataRecyclerView.setHasFixedSize(false);
        userArrayList = db.getUser();
        adapter = new UserAdapter(userArrayList);
        userDataRecyclerView.setAdapter(adapter);
    }


    @OnClick(R.id.fab)
    protected void onClick() {
        startActivityForResult(new Intent(this, UserFormActivity.class), RC_ADD_USER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_ADD_USER && resultCode == Activity.RESULT_OK && data != null) {
            String firstName = data.getStringExtra("first_name");
            String lastName = data.getStringExtra("last_name");
            String email = data.getStringExtra("email");

            userArrayList.add(new User(userArrayList.size(), firstName, lastName,email));
            adapter.notifyItemInserted(userArrayList.size());
            boolean isAdded = db.addUser(firstName, lastName,email);
            Toast.makeText(this, "" + isAdded, Toast.LENGTH_SHORT).show();
        }
    }
}

