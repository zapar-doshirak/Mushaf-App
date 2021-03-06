
package com.example.mushaf.view.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Surah;
import com.example.mushaf.view.adapters.SurahsAdapter;
import com.example.mushaf.viewmodel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeFragment extends Fragment implements SurahsAdapter.OnItemClickListener {

    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private SurahsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //делает нижнюю навигацию видимой
        BottomNavigationView navBar = requireActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);

        //RecyclerView stuff
        recyclerView = view.findViewById(R.id.surahs_home_list);
        adapter = new SurahsAdapter(this);
        recyclerView.setAdapter(adapter);

        //устанавливает viewmodel и наблюдатель
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getAllSures().observe(getViewLifecycleOwner(), new Observer<List<Surah>>() {
                    @Override
                    public void onChanged(List<Surah> surahs) {
                        adapter.setSurahs(surahs);
                    }
                });
        return view;
    }

    @Override
    public void onItemClick(Surah surah) {
        Fragment fragment = SurahFragment.newInstance(surah);
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    private void addItemsFromJSON() {
//        try {
//
//            String jsonDataString = readJSONDataFromFile();
//            JSONArray jsonArray = new JSONArray(jsonDataString);
//
//            for (int i = 0; i < jsonArray.length(); ++i) {
//
//                JSONObject itemObject = jsonArray.getJSONObject(i);
//
//                int number = Integer.parseInt(itemObject.getString("number"));
//                String name = itemObject.getString("englishName");
//                String place = itemObject.getString("revelationType");
//                int ayahsCount = Integer.parseInt(itemObject.getString("numberOfAyahs"));
//
//                Sures sures = new Sures(number, name, place, ayahsCount);
//                viewItems.add(sures);
//            }
////            Moshi moshi = new Moshi.Builder().build();
////            Type type = Types.newParameterizedType(List.class, Sures.class);
////            JsonAdapter<List<Sures>> jsonAdapter = moshi.adapter(type);
////            suresList = jsonAdapter.fromJson(jsonDataString);
//
////            assert sures != null;
////            Log.d("moshi", sures.toString());
//
//        } catch (IOException | JSONException e) {
//            Log.d(TAG, "addItemsFromJSON: ", e);
//        }
//    }
//
//    private String readJSONDataFromFile() throws IOException {
//        InputStream inputStream = null;
//        StringBuilder builder = new StringBuilder();
//
//        try {
//            String jsonString = null;
//            inputStream = getResources().openRawResource(R.raw.sures_meta);
//            BufferedReader bufferedReader =
//                    new BufferedReader(
//                            new InputStreamReader(inputStream, "UTF-8"));
//            while ((jsonString = bufferedReader.readLine()) != null) {
//                builder.append(jsonString);
//            }
//        } finally {
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//        return new String(builder);
//    }

    //Считает время выполнения процесса
//        long startTime = System.nanoTime();
//        ...some process...
//        long endTime = System.nanoTime();
//        long methodeDuration = (endTime - startTime);
//        Log.d(TAG, "Execute time: " + methodeDuration);
}