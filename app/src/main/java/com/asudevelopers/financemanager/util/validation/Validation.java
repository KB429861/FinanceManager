package com.asudevelopers.financemanager.util.validation;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

public abstract class Validation {

    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String AMOUNT_REGEX = "\\d+\\.\\d+";

    public static boolean isPhoneNumber(EditText editText, String error) {
        return isValid(editText, PHONE_REGEX, error, false);
    }

    public static boolean isAmount(EditText editText, String error) {
        return isValid(editText, AMOUNT_REGEX, error, true);
    }

    private static boolean isValid(
            EditText editText, String regex, String error, boolean required) {
        editText.setError(null);
        String text = editText.getText().toString().trim();
        if (required && !hasText(editText, error)) {
            return false;
        }
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(error);
            return false;
        }
        return true;
    }

    public static boolean isSelected(Spinner spinner, String error) {
        if (spinner.getSelectedItem() == null) {
            View selectedView = spinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView textView = (TextView) selectedView;
                textView.setError(error);
            }
            return false;
        }
        return true;
    }

    public static boolean hasText(EditText editText, String error) {
        editText.setError(null);
        String text = editText.getText().toString().trim();
        if (text.length() == 0) {
            editText.setError(error);
            return false;
        }
        return true;
    }
}
