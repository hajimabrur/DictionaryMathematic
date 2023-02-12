package com.ralya.dictimath.home.exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ralya.dictimath.R
import com.ralya.dictimath.home.exercise.model.Latihan

class ItemListAdapter(private var data: List<Latihan>,
                      private val listener: (Latihan) -> Unit)
    : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
            : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_exercise, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val tvKelas: TextView = view.findViewById(R.id.tv_kelas)
        private val tvNamaKelas: TextView = view.findViewById(R.id.tv_namakelas)
        private val tvSkor: TextView = view.findViewById(R.id.tv_skor)

        fun bindItem(data: Latihan, listener: (Latihan) -> Unit, context: Context) {
            tvKelas.setText(data.kelas)
            tvNamaKelas.setText(data.namakelas)
            tvSkor.setText(data.skor)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
