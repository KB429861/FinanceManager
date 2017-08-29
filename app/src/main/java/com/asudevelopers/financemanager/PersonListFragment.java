package com.asudevelopers.financemanager;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonListFragment extends ListFragment {

    String[] people = {"Ander Herera", "Diego Costa", "Juan Mata", "David De Gea",
            "Thibaut Courtouis", "Van Persie", "Oscar", "Thibaut Courtouis", "Van Persie", "Oscar"
            , "Thibaut Courtouis", "Van Persie", "Oscar", "Thibaut Courtouis", "Van Persie", "Oscar"};

    ArrayList<HashMap<String, String>> data = new ArrayList<>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HashMap<String, String> map;
        for (String person : people) {
            map = new HashMap<>();
            map.put("Name", person);
            map.put("Phone", "123");
            data.add(map);
        }

        String[] from = {"Name", "Phone"};
        int[] to = {R.id.tv_name, R.id.tv_phone};
        adapter = new SimpleAdapter(getActivity(), data, R.layout.fragment_person_item, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        super.onListItemClick(listView, view, position, id);
    }
}
