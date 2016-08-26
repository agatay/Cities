package com.example.mac.city;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    EditText mEdit;
    private ArrayAdapter<String> mCityAdapter;
    private List<String> list;

    public void addCity(String city){
        list.add(city);
        for(int i=0; i<list.size(); i++)
            Log.w("add", list.get(i));
       // mCityAdapter.clear();
       // mCityAdapter.addAll(list);
        mCityAdapter.notifyDataSetChanged();
        Log.w("fragment","addcity");
    }

    public void removeCity(Integer id) {
        //Log.w("fragment",list.get(id));
        list.remove(id);
        for(int i=0; i<list.size(); i++)
            Log.w("remove", list.get(i));
      //  mCityAdapter.clear();
      //  mCityAdapter.addAll(list);
        mCityAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        mEdit = (EditText) rootView.findViewById(R.id.editText);
        final ListView listview = (ListView) rootView.findViewById(R.id.city_list);
        listview.setAdapter(mCityAdapter);
        registerForContextMenu(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         /*       TextView txt = (TextView) adapterView.getChildAt(i - listview.firstVisiblePosition()).findViewById(R.id.list_item_city_textview);
                String keyword = txt.getText().toString();
               // String city = ((TextView) view).getText().toString();

                Log.w("asdasd",keyword); */
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + list.get(i));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
             if (i <= list.size())
                    Log.w("LIST LIST LIST",list.get(i));
            }
        }) ;
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
        for(int i=0; i<list.size(); i++)
            Log.w("remove", list.get(i));
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete:

                list.remove(info.position);
                mCityAdapter.notifyDataSetChanged();

                return true;
            case R.id.action_edit:
                mEdit.setText(list.get(info.position));
                mEdit.setSelection(0, list.get(info.position).length());
                list.remove(info.position);
                mCityAdapter.notifyDataSetChanged();
            default:
                return super.onContextItemSelected(item);
        }
    }
}
