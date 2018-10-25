package com.example.flycodeveloper.cevrem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MekanlarRVAdapter extends  RecyclerView.Adapter<MekanlarRVAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Mekanlar> mekanlarListe;

    public MekanlarRVAdapter(Context mContext, List<Mekanlar> mekanlarListe) {
        this.mContext = mContext;
        this.mekanlarListe = mekanlarListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yerler_card_tasarim,viewGroup,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu cardTasarimTutucu, int i) {
    Mekanlar mekan = mekanlarListe.get(i);
    cardTasarimTutucu.textViewMekanAdi.setText(mekan.getMekan_adi());
    cardTasarimTutucu.textViewLokasyon.setText(mekan.getEnlem() + " - " + mekan.getBoylam());
    cardTasarimTutucu.textViewAdres.setText(mekan.getAdres());

    }

    @Override
    public int getItemCount() {
        return mekanlarListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
    private TextView textViewMekanAdi, textViewLokasyon, textViewAdres;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewMekanAdi = itemView.findViewById(R.id.textViewMekandAdıItem);
            textViewLokasyon = itemView.findViewById(R.id.textViewEnlemBoylamItem);
            textViewAdres = itemView.findViewById(R.id.textViewAdresItem);

        }
    }
}
