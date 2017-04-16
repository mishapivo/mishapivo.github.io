package com.audacityit.audacity.settings;

import android.util.Log;
import android.widget.Filter;
import com.audacityit.audacity.adapter.PortfolioAdapter;
import com.audacityit.audacity.api.response_layer.portfolio.Portfolio_;
import com.audacityit.audacity.api.response_layer.portfolio.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imranaits on 10/6/16.
 */

public class PortfolioFilter extends Filter {

    private List<Portfolio_> contactList;
    private List<Portfolio_> filteredContactList;
    private PortfolioAdapter adapter;

    public PortfolioFilter(List<Portfolio_> contactList, PortfolioAdapter adapter) {
        this.adapter = adapter;
        this.contactList = contactList;
        this.filteredContactList = new ArrayList();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredContactList.clear();
        final FilterResults results = new FilterResults();
        //here you need to add proper items do filteredContactList
        if(constraint.length()==0){
            filteredContactList.addAll(contactList);
        }else {
            for (final Portfolio_ item : contactList) {
                for(Tag tag : item.getTags()) {
                    if (tag.getTag().contains(constraint)) {
                        filteredContactList.add(item);
                    }
                }
            }
        }

        results.values = filteredContactList;
        results.count = filteredContactList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setList(filteredContactList);
        adapter.notifyDataSetChanged();
    }
}
