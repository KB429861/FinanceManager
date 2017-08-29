package com.asudevelopers.financemanager;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PersonListFragment extends ListFragment {

    String data[] = new String[]{"one", "two", "three", "four"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }
}
