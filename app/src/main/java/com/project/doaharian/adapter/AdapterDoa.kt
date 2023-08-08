package com.project.doaharian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.doaharian.R
import com.project.doaharian.adapter.AdapterDoa.ListViewHolder
import com.project.doaharian.model.ModelDoa
import kotlinx.android.synthetic.main.list_item_doa.view.*
import java.util.*


class AdapterDoa(private val modelBacaan: MutableList<ModelDoa>  = mutableListOf()) :
    RecyclerView.Adapter<ListViewHolder>(), Filterable {

    private var modelBacaanListFull: List<ModelDoa> = listOf<ModelDoa>()

    override fun getFilter(): Filter {
        return modelBacaanFilter
    }

    private val modelBacaanFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelDoa> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(modelBacaanListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase()
                for (modelDoaFilter in modelBacaanListFull) {
                    if (modelDoaFilter.strTitle?.toLowerCase()?.contains(filterPattern) == true) {
                        filteredList.add(modelDoaFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            modelBacaan.clear()
            if (results.values is List<*>) {
                val filteredList = results.values as? List<ModelDoa>
                if (filteredList != null) {
                    modelBacaan.addAll(filteredList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_doa, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.tvId.text = dataModel.strId
        listViewHolder.tvTitle.text = dataModel.strTitle
        listViewHolder.tvArabic.text = dataModel.strArabic
        listViewHolder.tvLatin.text = dataModel.strLatin
        listViewHolder.tvTerjemahan.text = dataModel.strTranslation
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView
        var tvTitle: TextView
        var tvArabic: TextView
        var tvLatin: TextView
        var tvTerjemahan: TextView

        init {
            tvId = itemView.tvId
            tvTitle = itemView.tvTitle
            tvArabic = itemView.tvArabic
            tvLatin = itemView.tvLatin
            tvTerjemahan = itemView.tvTerjemahan
        }
    }

    init {
        modelBacaanListFull = ArrayList(modelBacaan)
    }

}