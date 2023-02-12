package com.ralya.dictimath.home.dashboard.detailpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.model.KelasSatu

class ClassVideoAdapter(private var data: List<KelasSatu>,
                        private val listener: (KelasSatu) -> Unit)
    : RecyclerView.Adapter<ClassVideoAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_desc, parent, false)
        return ViewHolder(inflatedView)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val titleClass: TextView = view.findViewById(R.id.tvTitleClass)
        private val timeClass: TextView = view.findViewById(R.id.tvTime)


        fun bindItem(data: KelasSatu, listener: (KelasSatu) -> Unit, context: Context) {
            titleClass.text = data.namakelas
            timeClass.text = data.waktukelas

            itemView.setOnClickListener {
                listener(data)
            }

        }

    }

}
