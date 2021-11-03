package com.example.mainscreen.ui.surah;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainscreen.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SurahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurahFragment extends Fragment {

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBar;
    private ImageView buttonBack;
    private ImageView buttonMore;
    private ConstraintLayout surahInfoBlock;
    private TextView surahsName;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public SurahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SurahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SurahFragment newInstance(String param1, String param2) {
        SurahFragment fragment = new SurahFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surah, container, false);

        //Инициализация и поиск всех вьюшек
//        CoordinatorLayout coordinatorLayout = view.findViewById(R.id.fragment_surah);
        FrameLayout surahBlock = view.findViewById(R.id.surahBlock);
        collapsingToolbar = view.findViewById(R.id.collapsingToolbar);
        appBar = view.findViewById(R.id.appBarLayout);
        buttonBack = view.findViewById(R.id.buttonBack);
        buttonMore = view.findViewById(R.id.buttonMore);
        surahInfoBlock = view.findViewById(R.id.surahInfoBlock);
        surahsName = view.findViewById(R.id.surahsName);
        NestedScrollView nestedScrollView = view.findViewById(R.id.nestedScrollView);
        //CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appBar.getLayoutParams();


        BottomNavigationView navBar = requireActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);


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




        return (view);
    }
}