package com.example.mushaf.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Sures;

import java.util.ArrayList;
import java.util.List;

public class SuresAdapter extends RecyclerView.Adapter<SuresAdapter.SuresHolder>{

    private static final int TYPE = 1;
    private List<Sures> suresList = new ArrayList<>();
    //устанавливает список сур
    public void setSures(List<Sures> suresList){
        this.suresList = suresList;
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    private final OnItemClickListener clickListener;
    public SuresAdapter(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SuresAdapter.SuresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE:
            default:
                //наполняет RecyclerList разметкой list_item
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.sures_list_item, parent, false);
                return new SuresHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SuresHolder holder, int position) {
        holder.bind(suresList.get(position), getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return suresList.size();
    }

    class SuresHolder extends RecyclerView.ViewHolder {

        private final TextView number;
        private final TextView name;
        private final TextView place;
        private final TextView ayahsCount;

        private Sures currentSurah;

        public SuresHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.surahsNumberView);
            name = itemView.findViewById(R.id.surahsNameView);
            place = itemView.findViewById(R.id.surahPlaceView);
            ayahsCount = itemView.findViewById(R.id.surahAyahsCountView);
        }

        void bind(Sures surah, int viewType){
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
        void onItemClick(Sures surah);
        }
    }