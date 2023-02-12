package com.ralya.dictimath.home.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.model.KelasDua

class KelasDuaAdapter(private var data: List<KelasDua>,
                      private val listener: (KelasDua) -> Unit)
    : RecyclerView.Adapter<KelasDuaAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
            : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_avalaible_class, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val courseTitle: TextView = view.findViewById(R.id.tvCourseTitle)
        private val courseGrade: TextView = view.findViewById(R.id.tvCourseGrade)
        private val coursePrice: TextView = view.findViewById(R.id.tvCoursePrice)
        private val classBanner: TextView = view.findViewById(R.id.ivClassBanner)

        fun bindItem(data: KelasDua, listener: (KelasDua) -> Unit, context: Context) {
            courseTitle.text = data.namakelas
            courseGrade.text = data.kelas
            coursePrice.text = data.hargakelas
            classBanner.text = data.bannerkelas

            itemView.setOnClickListener() {
                listener(data)
            }
        }
    }
}
