package com.asudevelopers.financemanager.base;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.asudevelopers.financemanager.R;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    @Override
    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(
                        getResources().getColor(R.color.text_color), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }
}
