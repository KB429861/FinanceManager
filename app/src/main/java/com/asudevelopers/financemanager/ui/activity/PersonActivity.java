package com.asudevelopers.financemanager.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.presenter.PersonPresenter;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity implements PersonView {

    private static final int RESULT_PICK_CONTACT = 65535;

    @BindView(R.id.et_name)
    EditText nameEditText;

    @BindView(R.id.et_phone)
    EditText phoneEditText;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @InjectPresenter
    PersonPresenter personPresenter;

    @ProvidePresenter
    PersonPresenter providePersonPresenter() {
        return new PersonPresenter(AppDatabase.getInstance(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        Person person = (Person) getIntent().getSerializableExtra("Person");
        personPresenter.loadAndShowPerson(person);

        toolbar.setTitle(R.string.person);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @OnClick(R.id.fab)
    public void onSaveClick(View view) {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        personPresenter.savePersonInfo(name, phone);
        onBackPressed();
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick(View view) {
        Intent contactPickerIntent = new Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        );
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        }
    }

    private void contactPicked(Intent data) {
        try {
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int phoneIndex = cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                String phone = cursor.getString(phoneIndex);
                showPersonInfo(name, phone);
                cursor.close();
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showPerson(Person person) {
        showPersonInfo(person.getName(), person.getPhone());
    }

    @Override
    public void showPersonInfo(String name, String phone) {
        nameEditText.setText(name);
        phoneEditText.setText(phone);
    }
}
