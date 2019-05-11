package com.example.schoolguidance.stu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.schoolguidance.R;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

public class MessagesActivity extends AppCompatActivity {

    QMUIGroupListView informationList;
    String temp;
    String str1 = "现在报道同学较多，大家可以错峰到校。";
    String str2 = "已经收到你的反馈，安排了7号志愿者前往你处帮忙。";
    String str3 = "如遇到自称学长学姐的推销人员，请拒绝购买推销产品并报告给保卫处，电话：85290110。";
    int t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        informationList = (QMUIGroupListView)findViewById(R.id.informationList);
        init();
    }



    private void init(){
        QMUICommonListItemView information1 = informationList.createItemView("来自辅导员的通知");//这里的mQMUIGroupListView是xml文件中控件
        information1.setOrientation(QMUICommonListItemView.VERTICAL);//设置为垂直模式，默认水平模式
        information1.setDetailText(str1);//在标题下方的详细信息

        //为QMUIGroupListView创建item项
        QMUICommonListItemView information2 = informationList.createItemView("请求帮助的反馈");
        information2.setOrientation(QMUICommonListItemView.VERTICAL);//设置为垂直模式
        information2.setDetailText(str2);//设置右边详细信息

        //为QMUIGroupListView创建item项
        QMUICommonListItemView information3 = informationList.createItemView("来自保卫处的通知");
        information3.setOrientation(QMUICommonListItemView.VERTICAL);//设置为垂直模式
        temp=str3;
        if(temp.length()>25)
        {
            temp=temp.substring(0,25);
            temp+="……";
        }
        information3.setDetailText(temp);//在标题下方的详细信息


        QMUIGroupListView.newSection(this)
//                .setTitle("Section 1")
//                .setDescription("Section 1 的描述")
                .addItemView(information1,onClickListener)
                .addItemView(information2,onClickListener)
                .addItemView(information3,onClickListener)
                .addTo(informationList);

    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getDetailText();
                new QMUIDialog.MessageDialogBuilder(MessagesActivity.this)
                        .setTitle("来自保卫处的通知")
                        .setMessage(text)
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
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
        }
    };
}