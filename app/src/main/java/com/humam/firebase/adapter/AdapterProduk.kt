package com.humam.firebase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.humam.firebase.R
import com.humam.firebase.model.ModelProduk

class AdapterProduk(var data: ArrayList<ModelProduk>) :
    RecyclerView.Adapter<AdapterProduk.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaProduk = view.findViewById<TextView>(R.id.tv_nama)
        val tvHargaProduk = view.findViewById<TextView>(R.id.tv_harga)
        val imgProduk = view.findViewById<ImageView>(R.id.img_produk)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNamaProduk.text = data[position].namaProduk
        holder.tvHargaProduk.text = data[position].hargaProduk
//cara get gambar local
        holder.imgProduk.setImageResource(data[position].gambarProduk)
        val cont = holder.itemView.context

    }

    override fun getItemCount(): Int {
        return data.size
    }
}