package com.example.mushaf.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Surah;

import java.util.ArrayList;
import java.util.List;

public class SurahsAdapter extends RecyclerView.Adapter<SurahsAdapter.SurahHolder>{

    private static final int TYPE = 1;
    private List<Surah> surahList = new ArrayList<>();

    //устанавливает список сур
    public void setSurahs(List<Surah> surahList){
        this.surahList = surahList;
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    private final OnItemClickListener clickListener;
    public SurahsAdapter(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SurahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.sures_list_item, parent, false);
                return new SurahHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SurahHolder holder, int position) {
        holder.bind(surahList.get(position), getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    class SurahHolder extends RecyclerView.ViewHolder {

        private final TextView number;
        private final TextView name;
        private final TextView place;
        private final TextView ayahsCount;

        private Surah currentSurah;

        public SurahHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_surah_item_text);
            name = itemView.findViewById(R.id.name_surah_item_text);
            place = itemView.findViewById(R.id.place_surah_item_text);
            ayahsCount = itemView.findViewById(R.id.ayahscount_surah_item_text);
        }

        void bind(Surah surah, int viewType){
            currentSurah = surah;
            switch (viewType){
                case TYPE:
                default:
                    number.setText(""+surah.getNumber());
                    name.setText(surah.getName());
                    place.setText(surah.getPlace());
                    ayahsCount.setText(""+surah.getAyahsCount());
            }
            //ClickListener для элемента списка сур
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(surah);
                }
            });
        }
    }

    //интерфейс для onItemClickListener
    public interface OnItemClickListener {
        void onItemClick(Surah surah);
        }
    }