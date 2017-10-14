package com.asudevelopers.financemanager.ui.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.asudevelopers.financemanager.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonListFragment extends ListFragment {

//    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        databaseHelper = DatabaseHelper.getInstance(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
//        loadPeople();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadPeople();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void loadPeople() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
//        List<Person> people = PersonContract.selectPeople(databaseHelper.getReadableDatabase());

//        HashMap<String, String> map;
//        for (Person person : people) {
//            map = new HashMap<>();
//            map.put("Name", person.getName());
//            map.put("Phone", person.getPhone());
//            data.add(map);
//        }

        String[] from = {"Name", "Phone"};
        int[] to = {R.id.tv_name, R.id.tv_phone};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.fragment_person_item, from, to);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        super.onListItemClick(listView, view, position, id);
    }
}
