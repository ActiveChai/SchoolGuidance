package com.example.schoolguidance.stu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.schoolguidance.R;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class PunchCardsActivity extends AppCompatActivity {

    String card1 = "预报到";
    String card2 = "报到注册";
    String card3 = "找宿舍";
    String card4 = "领取教材";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_punch_cards);
        init();

    }

    public void onClick(View view){
        String text="";
        switch (view.getId()){
            case R.id.pre_register:
                text = card1;
                break;
            case R.id.register:
                text = card2;
                break;
            case R.id.dormitory:
                text = card3;
                break;
            case R.id.books:
                text = card4;
                break;
        }
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("来自辅导员的通知")
                .setMessage(text)
                .addAction("了解", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("完成", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void init(){
        QMUIRoundButton preBtn = (QMUIRoundButton) findViewById(R.id.pre_register);
        QMUIRoundButton registerBtn = (QMUIRoundButton) findViewById(R.id.register);
        QMUIRoundButton dormitoryBtn = (QMUIRoundButton) findViewById(R.id.dormitory);
        QMUIRoundButton booksBtn = (QMUIRoundButton) findViewById(R.id.books);
    }
}
