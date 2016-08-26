package com.example.mac.city;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.AdapterView.AdapterContextMenuInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private ArrayAdapter<String> mCityAdapter;
    private List<String> list;
    private String[] cList = { };

    public void addCity(String city){
        list.add(city);
        mCityAdapter.notifyDataSetChanged();
        Log.w("fragment","addcity");
    }

    private void removeCity(Integer id) {
        //Log.w("fragment",list.get(id));
        list.remove(id);
        mCityAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("fragment","oncreate1");
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] cities = {};
        ArrayList<String> cityList = new ArrayList<String>(Arrays.asList(cities));

        list = cityList;
        mCityAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item,
                R.id.list_item_city_textview,
                list
        );
        Log.w("fragment","oncreate2");
        ListView listview = (ListView) rootView.findViewById(R.id.city_list);
        listview.setAdapter(mCityAdapter);
        registerForContextMenu(listview);

        Log.w("fragment","oncreate3");

/*        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {
                listview.showContextMenu();
                return true;
            } });
*/
        return rootView;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        MenuInflater inflater = getActivity().getMenuInflater();
       // menu.setHeaderTitle(list.get(info.position));
       // Log.w("rosssss",list.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.w("xdxdxd","zaaa");
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete:
                Log.w("htgdkjygdlk","sddsssdsd");
                removeCity(info.position);
                Log.w("143141",Integer.toString(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
