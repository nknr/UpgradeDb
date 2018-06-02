package com.nk.upgradedb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFormActivity extends AppCompatActivity {

    @BindView(R.id.first_name_input_layout)
    TextInputLayout firstNameInputLayout;
    @BindView(R.id.last_name_input_layout)
    TextInputLayout lastNameInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.submit)
    protected void onSubmit() {
        String firstName = Objects.requireNonNull(firstNameInputLayout.getEditText()).getText().toString().trim();
        String lastName = Objects.requireNonNull(lastNameInputLayout.getEditText()).getText().toString().trim();

        Intent intent = new Intent();
        intent.putExtra("first_name", firstName);
        intent.putExtra("last_name", lastName);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}