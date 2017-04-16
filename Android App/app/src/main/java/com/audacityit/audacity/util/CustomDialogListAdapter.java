package com.audacityit.audacity.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.audacityit.audacity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tushar on 30/1/2015.
 */

public class CustomDialogListAdapter extends ArrayAdapter<String> {

    private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();
    private String[] projectOptions = null;
    private LayoutInflater inflater;

    public CustomDialogListAdapter(Context context, int resource, int textViewResourceId,
                                   String[] projectOptions) {
        super(context, resource, textViewResourceId);
        inflater = LayoutInflater.from(context);
        this.projectOptions = projectOptions;
        for(int i=0; i< projectOptions.length; i++) {
            myChecked.put(i,false);
        }
    }


    public void toggleChecked(int position) {
        if (myChecked.get(position)) {
            myChecked.put(position, false);
        } else {
            myChecked.put(position, true);
        }
        notifyDataSetChanged();
    }

    public void checkAll() {
        for(int i=0; i< projectOptions.length; i++) {
            myChecked.put(i,true);
        }
        notifyDataSetChanged();
    }

    public void unCheckAll() {
        for(int i=0; i< projectOptions.length; i++) {
            myChecked.put(i,false);
        }
        notifyDataSetChanged();
    }

    public List<String> getCheckedItems() {
        List<String> selectedItems = new ArrayList<String>();
        for(int i=0; i< projectOptions.length; i++) {
            if (myChecked.get(i)) {
                selectedItems.add(projectOptions[i]);
            }
        }
        return selectedItems;
    }


//    public String getCheckedIDs() {
//        String checkedItems = "";
//        for (int i = 0; i < myChecked.size(); i++) {
//            if (myChecked.get(i)) {
//                checkedItems += categoryList.get(i).getId() + ".";
//            }
//        }
//        return checkedItems;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.layout_dialog_list_row, parent, false);
        }
        CheckedTextView checkedTextView = (CheckedTextView) row.findViewById(R.id.checkItemTv);
        checkedTextView.setText(projectOptions[position]);
        Boolean checked = myChecked.get(position);
        if (checked != null) {
            checkedTextView.setChecked(checked);
        }

        return row;
    }

//    public List<Integer> getCheckedItemPositions(){
//        List<Integer> checkedItemPositions = new ArrayList<Integer>();
//
//        for(int i = 0; i < myChecked.size(); i++){
//            if (myChecked.get(i)){
//                (checkedItemPositions).add(i);
//            }
//        }
//
//        return checkedItemPositions;
//    }

//    public List<String> getCheckedItems(){
//        List<String> checkedItems = new ArrayList<String>();
//
//        for(int i = 0; i < myChecked.size(); i++){
//            if (myChecked.get(i)){
//                (checkedItems).add(categories[i]);
//            }
//        }
//
//        return checkedItems;
//    }


    @Override
    public int getCount() {
        return projectOptions.length;
    }
}
