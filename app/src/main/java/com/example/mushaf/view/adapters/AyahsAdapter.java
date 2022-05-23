package com.example.mushaf.view.adapters;

import android.graphics.ImageDecoder;
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
import com.example.mushaf.data.net.response.ApiResponse;
import com.example.mushaf.data.net.response.SurahResponse;
import com.example.mushaf.data.net.response.Translation;

import java.util.ArrayList;
import java.util.List;

public class AyahsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE = 1;
    private List<Ayahs> ayahsList = new ArrayList<>();
    private List<Translation> ayahsTranslationList = new ArrayList<>();
    private Sures surah = new Sures();
    private final OnItemClickListener clickListener;

    public AyahsAdapter(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //устанавливает список аятов
    public void setAyahs(List<Ayahs> ayahsList, Sures surah){
        this.ayahsList = ayahsList;
        this.surah = surah;
        Log.d("SurahFragment", ayahsList.toString());
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    //    устанавливает список переводов аятов
    public void setAyahsTranslation(ApiResponse ayahsTranslation, Sures surah){
        this.ayahsTranslationList = ayahsTranslation.getSurahResponse().getTranslationList();
        this.surah = surah;
        Log.d("SurahFragment", "Ayah trans list is: " + ayahsTranslationList.toString());
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    //холдер для аятов
    public static class AyahsHolder extends RecyclerView.ViewHolder {

        private final TextView ayahNumber;
        private final TextView ayahText;
        private final TextView ayahTranslation;

        Ayahs currentAyah;

        public AyahsHolder(@NonNull View itemView) {
            super(itemView);
            ayahNumber = itemView.findViewById(R.id.ayahsNumberTextView);
            ayahText = itemView.findViewById(R.id.ayahsTextTextView);
            ayahTranslation = itemView.findViewById(R.id.ayahsTranslationTextView);
        }

        void bind(Ayahs ayah, Translation translation){
            currentAyah = ayah;
            ayahNumber.setText(""+ayah.getNumberInSurah());
            ayahText.setText(ayah.getAyahText());
            ayahTranslation.setText(translation.getAyahTranslation());
        }
    }

    //холдер для header
    public static class HeaderHolder extends RecyclerView.ViewHolder{

        private final TextView surahNumber;
        private final TextView surahName;
        private final TextView surahNameTranslationWithAyahsCount;

        Sures currentSurah;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            this.surahNumber = itemView.findViewById(R.id.surahsNumber);
            this.surahName = itemView.findViewById(R.id.surahsName);
            this.surahNameTranslationWithAyahsCount = itemView.findViewById(R.id.surahsNameTranslationWithVersesCount);
        }

        void bind(Sures surah){
            currentSurah = surah;
            surahNumber.setText(String.valueOf(surah.getNumber()));
            surahName.setText(surah.getName());
            surahNameTranslationWithAyahsCount.setText(surah.getNameTranslate() + ", " + surah.getAyahsCount() + " verses");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View layoutView;
        //загружает разметку в зависимости от позиции и возвращает нужный холдер
        switch (viewType) {
            case 0:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.header, parent, false);
                holder = new HeaderHolder(layoutView);
                break;
            case 1:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.ayah_list_item, parent, false);
                holder = new AyahsHolder(layoutView);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.bind(surah);
                break;
            case 1:
                AyahsHolder ayahsHolder = (AyahsHolder) holder;
                ayahsHolder.bind(ayahsList.get(position-1), ayahsTranslationList.get(position-1));
                Log.d("SurahFragment", "translation: " + ayahsTranslationList);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ayahsList.size()+1;
    }

    // по позиции определяет первый это элемент (для header) или нет
    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return 0;
        else
            return 1;
    }

    //интерфейс для onItemClickListener
    public interface OnItemClickListener {
        void onItemClick(Ayahs ayah);
        }
    }