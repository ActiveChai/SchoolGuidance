package com.example.schoolguidance.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class IssueInform extends AppCompatActivity {
    EditText edit_inform;
    QMUIRoundButton btn_submit_inform;
    TextView const_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_issue_inform);
        init();
    }
    private void init() {
        edit_inform = (EditText) findViewById(R.id.edit_inform);
        btn_submit_inform = (QMUIRoundButton) findViewById(R.id.btn_submit_inform);
        const_text = (TextView) findViewById(R.id.const_text);
    }


    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_submit_help:
                String text = edit_inform.getText().toString();
                if (text.equals("")) {
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                            .setTipWord("输入为空")
                            .create();
                    tipDialog.show();
                    const_text.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                        }
                    }, 1000);

                }
                break;

        }
    }
}
