package com.project.android.medicineremider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by FELIPE on 10/27/2016.
 * Customer Adapter for TypeMedicine Spinner.
 */

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    int[]iconSpinner;
    int[] TypeCategories;
    LayoutInflater inflter;

    public int[] getTypeCategories() {
        return TypeCategories;
    }

    public SpinnerAdapter(Context applicationContext, int[] iconSpinner , int[] TypeCategories) {

        this.context = applicationContext;
        this.iconSpinner = iconSpinner;
        this.TypeCategories = TypeCategories;
        inflter = (LayoutInflater.from(applicationContext));
    }



    @Override
    public int getCount() {
        return iconSpinner.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon_medicine);
        TextView names = (TextView) view.findViewById(R.id.type_medicine);
        icon.setImageResource(iconSpinner[i]);
        names.setText(TypeCategories[i]);
        return view;
    }
}
