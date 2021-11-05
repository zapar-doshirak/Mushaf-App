
package com.example.mushaf.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.models.Sures;
import com.example.mushaf.ui.surah.SurahFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerAdapter.ItemClickListener {

    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private List<Object> viewItems = new ArrayList<>();

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        //делает нижнюю навигацию видимой
        BottomNavigationView navBar = requireActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);

        //RecyclerView stuff
        recyclerView = (RecyclerView) view.findViewById(R.id.suresRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(viewItems, this);
        recyclerView.setAdapter(adapter);

        addItemsFromJSON();

        return view;


    }

    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);


            for (int i = 0; i < jsonArray.length(); ++i) {


                JSONObject itemObject = jsonArray.getJSONObject(i);

                int number = Integer.parseInt(itemObject.getString("number"));
                String name = itemObject.getString("englishName");
                String place = itemObject.getString("revelationType");
                int ayahsCount = Integer.parseInt(itemObject.getString("numberOfAyahs"));

                Sures sures = new Sures(number, name, place, ayahsCount);
                viewItems.add(sures);

            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.quran_meta);
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(inputStream, "UTF-8"));


            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);

    }

    @Override
    public void onItemClick(Sures sures) {

        Fragment fragment = new SurahFragment();

        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
