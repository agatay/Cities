package com.example.mac.city;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private void addCity(String city){
        cityList.add(city);
    }
    private ArrayList<String> cityList;
    private ArrayAdapter<String> mCityAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] cities = {};
        String value="";
        cityList = new ArrayList<String>(Arrays.asList(cities));

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            Log.w("1111", "zaaa");
            if(extras.getString("key") != null){
                Log.w("2222", extras.getString("key"));
                value = extras.getString("key");
                //The key argument here must match that used in the other activity
                if (value != null){
                    Log.w("3333", value);
                    cityList.add(value);
                }
            }
        }


        List<String> list = cityList;
        mCityAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item,
                R.id.list_item_city_textview,
                list
        );
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listview = (ListView) rootView.findViewById(R.id.city_list);
        listview.setAdapter(mCityAdapter);

        return rootView;
    }
}
