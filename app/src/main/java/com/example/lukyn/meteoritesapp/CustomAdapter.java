package com.example.lukyn.meteoritesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomAdapter extends ArrayAdapter<Meteorite> {
    @BindView(R.id.name)
    TextView meteoriteName;
    @BindView(R.id.mass)
    TextView meteoriteMass;
    @BindView(R.id.year)
    TextView meteoriteYear;


    private Context context;
    private List<Meteorite> values;
    private Meteorite item;

    public CustomAdapter(Context context, List<Meteorite> values) {
        super(context, R.layout.meteorite_item, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.meteorite_item, parent, false);
        }
        ButterKnife.bind(this, row);

        item = values.get(position);
        meteoriteName.setText(item.getMeteoriteName());
        meteoriteMass.setText(ModifyData.modifyMass(Float.valueOf(item.getMeteoriteMass())));
        meteoriteYear.setText(item.getMeteoriteFall() + ": " + ModifyData.modifyYear(item.getMeteoriteYear()));
        MainActivity.mProgressBar.setVisibility(View.GONE);

        return row;
    }
}