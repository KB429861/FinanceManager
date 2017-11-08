package com.asudevelopers.financemanager.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.presenter.PersonPresenter;
import com.asudevelopers.financemanager.mvp.view.PersonView;
import com.asudevelopers.financemanager.util.validation.TextValidator;
import com.asudevelopers.financemanager.util.validation.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        registerViews();
    }

    private void registerViews() {
        nameEditText.addTextChangedListener(new TextValidator(nameEditText) {
            @Override
            public void validate(EditText editText) {
                Validation.hasText(editText, getString(R.string.msg_empty_field));
            }
        });
        phoneEditText.addTextChangedListener(new TextValidator(phoneEditText) {
            @Override
            public void validate(EditText editText) {
                Validation.isPhoneNumber(editText, getString(R.string.msg_invalid_phone));
            }
        });
    }

    private void save() {
        if (isValid()) {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            personPresenter.savePersonInfo(name, phone);
            onBackPressed();
        }
    }

    private boolean isValid() {
        return Validation.hasText(nameEditText, getString(R.string.msg_empty_field)) &&
                Validation.isPhoneNumber(phoneEditText, getString(R.string.msg_invalid_amount));
    }

    private void search() {
        Intent contactPickerIntent = new Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        );
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    private void delete() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.alert_deleting_person_title)
                .setMessage(R.string.msg_deleting_person)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        personPresenter.deletePerson();
                        onBackPressed();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person, menu);
        MenuItem deleteItem = menu.findItem(R.id.action_delete);
        if (personPresenter.isEditMode()) {
            deleteItem.setVisible(true);
        } else {
            deleteItem.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            save();
            return true;
        } else if (id == R.id.action_search) {
            search();
            return true;
        } else if (id == R.id.action_delete) {
            delete();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            Cursor cursor = null;
            if (uri != null) {
                cursor = getContentResolver().query(
                        uri, null, null, null, null);
            }
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
    public void showPerson(Person person) {
        showPersonInfo(person.getName(), person.getPhone());
    }

    @Override
    public void showPersonInfo(String name, String phone) {
        nameEditText.setText(name);
        phoneEditText.setText(phone);
    }
}
