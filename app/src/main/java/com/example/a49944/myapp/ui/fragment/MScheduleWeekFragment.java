package com.example.a49944.myapp.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.sdk.ConfigManager;

import java.lang.reflect.Field;

/**
 * Created by 49944
 * Time: 2019/5/11 11:52
 * Des:
 */
public class MScheduleWeekFragment extends AppCompatDialogFragment {
    private OnClickQueryScheduleWeekListenser listenser;
    private static final String TAG = MScheduleWeekFragment.class.getSimpleName();
    private View mView;
    private NumberPicker mNumberPicker;
    private ConfigManager mConfigManager;
    private final String[] date = {
            "1", "2",
            "3", "4",
            "5", "6",
            "7", "8",
            "9", "10",
            "11", "12",
            "13", "14",
            "15", "16",
            "17", "18",
            "19", "20",
            "21", "22"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mConfigManager = ConfigManager.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mView = layoutInflater.inflate(R.layout.activity_select_item_inquire_grade_layout, null);
        initNumberPicker();
        View view = layoutInflater.inflate(R.layout.fragment_custom_dialog_title, null);
        TextView tvWeek = view.findViewById(R.id.tv_week_dialog);
        tvWeek.setText("选择周数");
        builder.setView(mView)
                .setCustomTitle(view)
                .setPositiveButton(R.string.bt_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int value = mNumberPicker.getValue();
                        mConfigManager.setScheduleWeek(date[value]);
                        listenser.onClick();
                    }
                })
                .setNegativeButton(R.string.btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MScheduleWeekFragment.this.getDialog().cancel();

                    }
                });
        return builder.create();
    }
    /**
     * 初始化数字选择器
     */
    private void initNumberPicker() {
        mNumberPicker = mView.findViewById(R.id.select_date);
        mNumberPicker.setDisplayedValues(date);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(date.length - 1);
        setPickerDividerColor();
    }
    /**
     * 通过反射改变分割线颜色,
     */
    private void setPickerDividerColor() {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    pf.set(mNumberPicker, new ColorDrawable(0xFF5294FE));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setOnClickQueryScheduleListenser(OnClickQueryScheduleWeekListenser listenser){
        this.listenser = listenser;
    }

    public interface OnClickQueryScheduleWeekListenser{
        void onClick();
    }
}
