package com.example.mushaf.view.ui;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;
import com.example.mushaf.viewmodel.HomeViewModel;
import com.example.mushaf.viewmodel.SurahViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SurahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurahFragment extends Fragment {

    private static final String TAG = "SurahFragment";

    private SurahViewModel surahViewModel;
    private HomeViewModel homeViewModel;

    //views
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBar;
    private ImageView buttonBack;
    private ImageView buttonMore;
    private ConstraintLayout surahInfoBlock;
    private TextView surahsNumberView;
    private TextView surahsNameView;
    private TextView surahsNameTranslationWithAyahsCountView;

    private Sures surah;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String THIS_SURAH = "this surah";

    public SurahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SurahFragment.
     */
    public static SurahFragment newInstance(Sures surah) {
        SurahFragment fragment = new SurahFragment();
        Log.d(TAG, "Bundle from HomeFragment is: " + surah);
        Bundle args = new Bundle();
        args.putParcelable(THIS_SURAH, surah);
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
        // Наполняет layout этим фрагментом
        View view = inflater.inflate(R.layout.into_fragment_surah, container, false);

        // Прячет нижнюю навигацию
        BottomNavigationView navBar = requireActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

        //find views
//      CoordinatorLayout coordinatorLayout = view.findViewById(R.id.fragment_surah);
        FrameLayout surahBlock = view.findViewById(R.id.surahBlock);
        collapsingToolbar = view.findViewById(R.id.collapsingToolbar);
        appBar = view.findViewById(R.id.appBarLayout);
        buttonBack = view.findViewById(R.id.buttonBack);
        buttonMore = view.findViewById(R.id.buttonMore);
        surahInfoBlock = view.findViewById(R.id.surahInfoBlock);
        NestedScrollView nestedScrollView = view.findViewById(R.id.nestedScrollView);
        //header
        surahsNumberView = view.findViewById(R.id.surahsNumber);
        surahsNameView = view.findViewById(R.id.surahsName);
        surahsNameTranslationWithAyahsCountView = view.findViewById(R.id.surahsNameTranslationWithAyahsCount);


        //Смотрит когда toolbar collapsed и убирает/добавляет кнопки
        appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {

                // Collapsed
                buttonBack.animate().alpha(0.0f).setDuration(50);
                buttonMore.animate().alpha(0.0f).setDuration(50);


            } else if (verticalOffset == 0) {

                // Expanded
                buttonBack.animate().alpha(1.0f).setDuration(150);
                buttonMore.animate().alpha(1.0f).setDuration(150);
            }
        });

        //создает ViewModelProvider и ставит наблюдателя на список с аятами
        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        surahViewModel.getAllAyahs().observe(getViewLifecycleOwner(), new Observer<List<Ayahs>>() {
            @Override
            public void onChanged(List<Ayahs> sures) {

            }
        });

        // заполняет header
        int surahsNumber = surah.getNumber();
        String surahsName = surah.getName();
        String surahsNameTranslation = surah.getNameTranslate();
        int ayahsCount = surah.getAyahsCount();
        String surahsNameTranslationWithAyahsCount = surahsNameTranslation + ", " + ayahsCount + " verses";

        surahsNumberView.setText(String.valueOf(surahsNumber));
        surahsNameView.setText(surahsName);
        surahsNameTranslationWithAyahsCountView.setText(surahsNameTranslationWithAyahsCount);


        return (view);
    }

}