package com.example.mushaf.view.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Ayah;
import com.example.mushaf.data.model.Surah;
import com.example.mushaf.view.adapters.AyahsAdapter;
import com.example.mushaf.viewmodel.SurahViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SurahFragment extends Fragment implements AyahsAdapter.OnItemClickListener {

    private static final String TAG = "SurahFragment";
    private SurahViewModel surahViewModel;

    private RecyclerView ayahsRecyclerView;
    private AyahsAdapter adapter;

    private Surah surah;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String THIS_SURAH = "this surah";

    public SurahFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SurahFragment.
     */
    public static SurahFragment newInstance(Surah surahNumber) {
        SurahFragment fragment = new SurahFragment();
        Bundle args = new Bundle();
        args.putParcelable(THIS_SURAH, surahNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            surah = getArguments().getParcelable(THIS_SURAH);
            Log.d(TAG, surah.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.into_fragment_surah, container, false);

        // Прячет нижнюю навигацию
        BottomNavigationView navBar = requireActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

        //Смотрит когда toolbar collapsed и убирает/добавляет кнопки
//        appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
//
//            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
//                // Collapsed
//                buttonBack.animate().alpha(0.0f).setDuration(50);
//                buttonMore.animate().alpha(0.0f).setDuration(50);
//            } else if (verticalOffset == 0) {
//                // Expanded
//                buttonBack.animate().alpha(1.0f).setDuration(150);
//                buttonMore.animate().alpha(1.0f).setDuration(150);
//            }
//        });
//
        // заполняет header
        int surahsNumber = surah.getNumber();

        //RecyclerView stuff
        ayahsRecyclerView = view.findViewById(R.id.ayahs_surah_list);
        ayahsRecyclerView.setHasFixedSize(true);
        adapter = new AyahsAdapter(this);

        //создает ViewModelProvider и ставит наблюдателя на список с аятами
        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        surahViewModel.getSurahAyahs(surahsNumber).observe(getViewLifecycleOwner(), new Observer<List<Ayah>>() {
            @Override
            public void onChanged(List<Ayah> ayahs) {
                Log.d(TAG, "Ayahs of this surah: " + ayahs);
                adapter.setAyahs(ayahs, surah);
            }
        });

        //todo: сделать, чтобы перевод загружался в бд, а по Retrofit только изменение версии в бд
        surahViewModel.getAyahsTranslation(surahsNumber, "ru.kuliev").observe(getViewLifecycleOwner(), response -> {
                if(response!=null){
                    Log.d(TAG, "Translation of this surah: " + response);
                    adapter.setAyahsTranslation(response, surah);
                    ayahsRecyclerView.setAdapter(adapter);
                } else{
                    Toast.makeText(getActivity(), "Please check your internet connection or try again later", Toast.LENGTH_LONG).show();
                }
        });
        return (view);
    }

    @Override
    public void onItemClick(Ayah ayah) {
        //todo: поставить сюда отображение фрагмента с аятом с подробностями
        Log.d(TAG, "OnAyahClick in SurahFragment: " + ayah);
    }
}