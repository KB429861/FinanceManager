package com.asudevelopers.financemanager.util.validation;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

public abstract class Validation {

    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String AMOUNT_REGEX = "\\d+\\.\\d+";

    public static boolean isPhoneNumber(EditText editText, String message) {
        return isValid(editText, PHONE_REGEX, message, false);
    }

    public static boolean isAmount(EditText editText, String message) {
        return isValid(editText, AMOUNT_REGEX, message, true);
    }

    private static boolean isValid(EditText editText, String regex, String message, boolean required) {
        editText.setError(null);
        String text = editText.getText().toString().trim();
        if (required && !hasText(editText, message)) {
            return false;
        }
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(message);
            return false;
        }
        return true;
    }

    public static boolean isSelected(Spinner spinner, String message) {
        if (spinner.getSelectedItem() == null) {
            View selectedView = spinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView textView = (TextView) selectedView;
                textView.setError(message);
            }
            return false;
        }
        return true;
    }

    public static boolean hasText(EditText editText, String message) {
        editText.setError(null);
        String text = editText.getText().toString().trim();
        if (text.length() == 0) {
            editText.setError(message);
            return false;
        }
        return true;
    }
}
