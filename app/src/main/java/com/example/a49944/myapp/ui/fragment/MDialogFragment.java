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
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.net.grade.InquireGradeYearEvent;

import java.lang.reflect.Field;

/**
 * Created by 49944
 * Time: 2019/5/9 17:15
 * Des:
 */
public class MDialogFragment extends AppCompatDialogFragment {
    private View mView;

    private OnClickQueryGradeListenser listenser;

    private NumberPicker mNumberPicker;
    private final String[] date = {
            "2021-2022-1", "2021-2022-2",
            "2020-2021-1", "2020-2021-2",
            "2019-2020-1", "2019-2020-2",
            "2018-2019-1", "2018-2019-2",
            "2017-2018-1", "2017-2018-2",
            "2016-2017-1", "2016-2017-2",
            "2015-2016-1", "2015-2016-2",
            "2014-2015-1", "2014-2015-2",
            "2013-2014-1", "2013-2014-2",
            "2012-2013-1", "2012-2013-2",
            "2011-2012-1", "2011-2012-2",
            "2010-2011-1", "2010-2011-2",
            "2009-2010-1", "2009-2010-2"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mView = layoutInflater.inflate(R.layout.activity_select_item_inquire_grade_layout, null);
        initNumberPicker();
        builder.setView(mView)
                .setCustomTitle(layoutInflater.inflate(R.layout.fragment_custom_dialog_title, null))
                .setPositiveButton(R.string.bt_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 2019/5/9
                        int value = mNumberPicker.getValue();
                        InquireGradeYearEvent.setDate(date[value]);
                       // InquireGradeYearEvent.setIsClickQuire(true);
                        listenser.onClick();
                    }
                })
                .setNegativeButton(R.string.btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MDialogFragment.this.getDialog().cancel();
                        //InquireGradeYearEvent.setIsClickQuire(false);
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

    public void setOnClickQueryGradeListenser(OnClickQueryGradeListenser listenser){
        this.listenser = listenser;
    }

    public interface OnClickQueryGradeListenser{
        void onClick();
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
}
