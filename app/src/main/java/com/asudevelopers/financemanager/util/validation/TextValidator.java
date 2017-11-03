package com.asudevelopers.financemanager.util.validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class TextValidator implements TextWatcher {

    private final EditText editText;

    protected TextValidator(EditText editText) {
        this.editText = editText;
    }

    public abstract void validate(EditText editText);

    @Override
    public void afterTextChanged(Editable s) {
        validate(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
