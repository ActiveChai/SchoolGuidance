package com.example.schoolguidance.admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.schoolguidance.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by zengd on 2016/8/17 0017.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<ItemBean> mName;
    private List<ItemBean> mTime;
    private List<ItemBean> mPlace;
    private List<ItemBean> mPs;
    private Context mContext;

    public ListViewAdapter(Context mContext, List<ItemBean> mData1, List<ItemBean> mData2,List<ItemBean> mData3, List<ItemBean> mData4) {
        this.mContext = mContext;
        this.mName = mData1;
        this.mTime=mData2;
        this.mPlace = mData3;
        this.mPs=mData4;
    }

    @Override
    public int getCount() {
        return mName.size();
    }

    @Override
    public Object getItem(int position) {
        return mName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.admin_item_issue_step, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ItemBean itemObjName = mName.get(position);
        final ItemBean itemObjTime = mTime.get(position);
        final ItemBean itemObjPlace = mPlace.get(position);
        final ItemBean itemObjPs = mPs.get(position);
        //This is important. Remove TextWatcher first.
        //name
        if (holder.editName.getTag() instanceof TextWatcher) {
            holder.editName.removeTextChangedListener((TextWatcher) holder.editName.getTag());
        }

        holder.editName.setText(itemObjName.getText());

        TextWatcher watcherName = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    itemObjName.setText("");
                } else {
                    itemObjName.setText(s.toString());
                }
            }
        };

        holder.editName.addTextChangedListener(watcherName);
        holder.editName.setTag(watcherName);
        //time
        if (holder.editTime.getTag() instanceof TextWatcher) {
            holder.editTime.removeTextChangedListener((TextWatcher) holder.editTime.getTag());
        }
        final ViewHolder finalHolder = holder;
        final ViewHolder finalHolder1 = holder;
        holder.editTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    Calendar calendar=Calendar.getInstance();
                    DatePickerDialog datePickerDialog=new DatePickerDialog(ListViewAdapter.this.mContext,R.style.ThemeDialog, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            String pattern="00";
                            java.text.DecimalFormat dfDate = new java.text.DecimalFormat(pattern);
                            finalHolder.editTime.setText(i+"-"+dfDate.format(i1+1)+"-"+dfDate.format(i2));
                        }
                    },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));


//                    Calendar calendar=Calendar.getInstance();
                    TimePickerDialog timePickerDialog=new TimePickerDialog(ListViewAdapter.this.mContext, R.style.ThemeDialog,new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            String pattern="00";
                            java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
                            finalHolder1.editTime.setText(finalHolder.editTime.getText()+" "+df.format(i)+":"+df.format(i1));
                        }
                    },calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),true);
                    timePickerDialog.show();
                    datePickerDialog.show();
                    return true;
                }

                return false;
            }
        });
//        holder.editTime.setText(df.format(i)+":"+df.format(i1));
        holder.editTime.setText(itemObjTime.getText());

        TextWatcher watcherTime = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("clickb");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    itemObjTime.setText("");
                } else {
                    itemObjTime.setText(s.toString());
                }
            }
        };

        holder.editTime.addTextChangedListener(watcherTime);
        holder.editTime.setTag(watcherTime);

        //place
        if (holder.editPlace.getTag() instanceof TextWatcher) {
            holder.editPlace.removeTextChangedListener((TextWatcher) holder.editPlace.getTag());
        }

        holder.editPlace.setText(itemObjPlace.getText());

        TextWatcher watcherPlace = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    itemObjPlace.setText("");
                } else {
                    itemObjPlace.setText(s.toString());
                }
            }
        };

        holder.editPlace.addTextChangedListener(watcherPlace);
        holder.editPlace.setTag(watcherPlace);
        //ps
        if (holder.editPs.getTag() instanceof TextWatcher) {
            holder.editPs.removeTextChangedListener((TextWatcher) holder.editPs.getTag());
        }

        holder.editPs.setText(itemObjPs.getText());

        TextWatcher watcherPs = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    itemObjPs.setText("");
                } else {
                    itemObjPs.setText(s.toString());
                }
            }
        };
        holder.editPs.addTextChangedListener(watcherPs);
        holder.editPs.setTag(watcherPs);

        return convertView;
    }

    private class ViewHolder {
        private EditText editName;
        private EditText editTime;
        private EditText editPlace;
        private EditText editPs;

        public ViewHolder(View convertView) {
            editName = (EditText) convertView.findViewById(R.id.step_name);
            editTime = (EditText) convertView.findViewById(R.id.step_time);
            editPlace = (EditText) convertView.findViewById(R.id.step_place);
            editPs = (EditText) convertView.findViewById(R.id.step_ps);
        }
    }
}
