package com.ralya.dictimath.home.dictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dictionary.model.Dictionary

class ItemListAdapter(private var data: List<Dictionary>,
                      private val listener: (Dictionary) -> Unit)
    : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType : Int
    ) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_listitem, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder : ViewHolder, postition:Int) {
        holder.bindItem(data[postition], listener, contextAdapter)
    }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val tvIstilah: TextView = view.findViewById(R.id.tvIstilah)

            fun bindItem(data: Dictionary, listener: (Dictionary) -> Unit, context: Context) {
                tvIstilah.text = data.Istilah

                itemView.setOnClickListener() {
                    listener(data)
                }

            }
        }
}
