package com.repo.repolist.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.repo.repolist.R;

/**
 * Created by Doha on 09/07/19.
 */
public class RepoListBaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);
        progressDialog.setCancelable(false);

    }

    protected void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }
}