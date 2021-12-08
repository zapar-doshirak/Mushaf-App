package com.example.mushaf.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;

import java.util.ArrayList;
import java.util.List;

public class AyahsAdapter extends RecyclerView.Adapter<AyahsAdapter.AyahsHolder>{

    private static final int TYPE = 1;
    private List<Ayahs> ayahsList = new ArrayList<>();
    private final RecyclerViewOnClickListener clickListener;

    public AyahsAdapter(RecyclerViewOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class AyahsHolder extends RecyclerView.ViewHolder {

        private final TextView ayahNumber;
        private final TextView ayahText;
        private final TextView ayahTranslation;

        public AyahsHolder(@NonNull View itemView) {
            super(itemView);

            ayahNumber = itemView.findViewById(R.id.ayahsNumberTextView);
            ayahText = itemView.findViewById(R.id.ayahsTextTextView);
            ayahTranslation = itemView.findViewById(R.id.ayahsTranslationTextView);
        }
    }

    @NonNull
    @Override
    public AyahsAdapter.AyahsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case TYPE:

            default:

                //наполняет RecyclerList разметкой list_item
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.ayah_list_item, parent, false);

                return new AyahsHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AyahsHolder holder, int position) {

        int viewType = getItemViewType(position);
        Ayahs ayah = ayahsList.get(position);


        switch (viewType){
            case TYPE:
            default:

//
//
//          Log.d("SurahFragment", ayah.getNumber() + ayah.getAyahText());
                holder.ayahNumber.setText(""+ayah.getNumberInSurah());
                holder.ayahText.setText(ayah.getAyahText());
                //todo: сюда установку перевода, полученного через апи

        }

        //ClickListener для элемента списка сур
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(ayah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ayahsList.size();
    }

    //устанавливает список сур
    public void setAyahs(List<Ayahs> ayahsList){
        this.ayahsList = ayahsList;
        Log.d("SurahFragment", ayahsList.toString());
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    //интерфейс для onItemClickListener
    public interface RecyclerViewOnClickListener {
        void onItemClick(Ayahs ayah);

        }
    }
