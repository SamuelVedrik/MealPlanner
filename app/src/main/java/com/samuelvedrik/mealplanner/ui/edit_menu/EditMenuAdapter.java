package com.samuelvedrik.mealplanner.ui.edit_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.samuelvedrik.mealplanner.R;

import java.util.ArrayList;

public class EditMenuAdapter extends BaseAdapter {

    private ArrayList<String> menu;
    private Context context;
    private DeleteListener listener;

    public EditMenuAdapter(Context context, ArrayList<String> menu, DeleteListener listener){
        super();
        this.context = context;
        this.menu = menu;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return menu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customlayout, null);
        }

        TextView textView = view.findViewById(R.id.textView);
        ImageButton delete = view.findViewById(R.id.deleteButton);

        textView.setText(menu.get(position));
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.notifyDelete(position);
            }
        });

        return view;
    }
}
