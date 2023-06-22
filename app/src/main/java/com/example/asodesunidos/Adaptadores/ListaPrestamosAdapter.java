package com.example.asodesunidos.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asodesunidos.Modelos.prestamo;
import com.example.asodesunidos.R;
import com.example.asodesunidos.Control.verPrestamo;

import java.util.ArrayList;

public class ListaPrestamosAdapter extends RecyclerView.Adapter<ListaPrestamosAdapter.PrestamoViewHolder> {

    ArrayList<prestamo> listaPrestamos;

    public ListaPrestamosAdapter(ArrayList<prestamo> listaPrestamos){
        this.listaPrestamos = listaPrestamos;
    }

    @NonNull
    @Override
    public PrestamoViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_prestamo, null, false);
        return new PrestamoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestamoViewHolder holder, int position){
        holder.viewTipoCredito.setText(listaPrestamos.get(position).getTipoPrestamo());
        holder.viewMonto.setText(String.valueOf(listaPrestamos.get(position).getMontoPrestamo()));
        holder.viewCuota.setText(String.valueOf(listaPrestamos.get(position).getMontoCuota()));
    }

    @Override
    public int getItemCount(){
        return listaPrestamos.size();
    }

    public class PrestamoViewHolder extends RecyclerView.ViewHolder{

        TextView viewTipoCredito, viewMonto, viewCuota;
        public PrestamoViewHolder(@NonNull View itemView){
            super(itemView);

            viewTipoCredito = itemView.findViewById(R.id.viewTipoCredito);
            viewMonto = itemView.findViewById(R.id.viewMonto);
            viewCuota = itemView.findViewById(R.id.viewCuota);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent = new Intent(context, verPrestamo.class);
                    intent.putExtra("PRESTAMO",listaPrestamos.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }

}
