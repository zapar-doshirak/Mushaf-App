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

public class AyahsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

//    private static final int TYPE = 1;
    private List<Ayahs> ayahsList = new ArrayList<>();
//    private List<AyahsTranslation> ayahsTranslationList = new ArrayList<>();
    private Sures surah = new Sures();
    private final RecyclerViewOnClickListener clickListener;

    public AyahsAdapter(RecyclerViewOnClickListener clickListener) {
        this.clickListener = clickListener;
    }


    //холдер для аятов
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

    //холдер для header
    public static class HeaderHolder extends RecyclerView.ViewHolder{

        private final TextView surahNumber;
        private final TextView surahName;
        private final TextView surahNameTranslationWithAyahsCount;
//        private final ImageView buttonBack;
//        private final ImageView buttonMore;


        public HeaderHolder(@NonNull View itemView) {
            super(itemView);

            this.surahNumber = itemView.findViewById(R.id.surahsNumber);
            this.surahName = itemView.findViewById(R.id.surahsName);
            this.surahNameTranslationWithAyahsCount = itemView.findViewById(R.id.surahsNameTranslationWithVersesCount);
//            this.buttonBack = itemView.findViewById(R.id.buttonBack);
//            this.buttonMore = itemView.findViewById(R.id.buttonMore);
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
                //наполняет RecyclerList разметкой list_item
                layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.ayah_list_item, parent, false);
                holder = new AyahsHolder(layoutView);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return holder;

    }

    // по позиции определяет первый это элемент (для header) или нет
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        Ayahs ayah;
//        AyahsTranslation ayahTranslation;

        switch (holder.getItemViewType()){
            case 0:
                HeaderHolder h0 = (HeaderHolder) holder;
                h0.surahNumber.setText(String.valueOf(surah.getNumber()));
                h0.surahName.setText(surah.getName());
                h0.surahNameTranslationWithAyahsCount.setText(surah.getNameTranslate() + ", " + surah.getAyahsCount() + " verses");
//                h0.buttonBack.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
                break;

            case 1:

                ayah = ayahsList.get(position-1);
                AyahsHolder h1 = (AyahsHolder) holder;
//          Log.d("SurahFragment", ayah.getNumber() + ayah.getAyahText());
                h1.ayahNumber.setText(""+ayah.getNumberInSurah());
                h1.ayahText.setText(ayah.getAyahText());
                //todo: сюда установку перевода, полученного через апи
//                ayahTranslation = ayahsTranslationList.get(position-1);
//                h1.ayahTranslation.setText(ayahTranslation.getAyahTranslation());

                //ClickListener для элемента списка сур
                h1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(ayah);
                    }
                });
                break;

        }




    }

    @Override
    public int getItemCount() {
        return ayahsList.size()+1;
    }

    //устанавливает список аятов
    public void setAyahs(List<Ayahs> ayahsList, Sures surah){
        this.ayahsList = ayahsList;
        this.surah = surah;
        Log.d("SurahFragment", ayahsList.toString());
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    //устанавливает список переводов аятов
//    public void setAyahsTranslation(List<AyahsTranslation> ayahsTranslationList, Sures surah){
//        this.ayahsTranslationList = ayahsTranslationList;
//        this.surah = surah;
//        Log.d("SurahFragment", ayahsTranslationList.toString());
//        //todo: notifyDataSetChanged() заменить на более хорошее решение
//        notifyDataSetChanged();
//    }


    //интерфейс для onItemClickListener
    public interface RecyclerViewOnClickListener {
        void onItemClick(Ayahs ayah);
        }
    }
