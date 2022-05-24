package com.ibnu.exitkomputerstore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_item.view.*
import java.text.DecimalFormat


class ItemAdapter(
    var results: ArrayList<ItemModel.Result>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = results[position]
        var jumlah = 0
        val decFormat = DecimalFormat("#,###")

        Glide.with(holder.view)
            .load(result.image)
            .placeholder(R.drawable.ic_baseline_personal_video_24)
            .error(R.drawable.ic_baseline_personal_video_24)
            .centerCrop()
            .into(holder.view.iv_frItem_imageProduk)

        holder.view.tv_frItem_namaProduk.text = result.nama
        holder.view.tv_frItem_hargaProduk.text = "Rp. ${decFormat.format(result.harga)}"
        holder.view.tv_frItem_deskripsiProduk.text = result.deskripsi

        holder.view.btn_frItem_plus.setOnClickListener {
            if (jumlah < 10) {

                ++jumlah

                holder.view.tv_frItem_number.text = jumlah.toString()
                holder.view.tv_frItem_totalHargaProduk.text =
                    "Rp. ${decFormat.format(jumlah * result.harga)}"
            }
        }
        holder.view.btn_frItem_minus.setOnClickListener {
            when {
                jumlah > 0 -> {
                    --jumlah

                    holder.view.tv_frItem_number.text = jumlah.toString()
                    holder.view.tv_frItem_totalHargaProduk.text =
                        "Rp. ${decFormat.format(jumlah * result.harga)}"

                    when {
                        jumlah == 0 -> {
                            holder.view.tv_frItem_totalHargaProduk.text = ""

                        }
                    }
                }
            }

        }
    }

    override fun getItemCount() = results.size

    interface OnAdapterListener {
        fun onClick(result: ItemModel.Result)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(data: List<ItemModel.Result>) {
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
}