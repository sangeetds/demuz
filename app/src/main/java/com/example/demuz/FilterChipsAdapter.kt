package com.example.demuz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip

class FilterChipsAdapter(
    private val values: List<String>
) : RecyclerView.Adapter<FilterChipsAdapter.ViewHolder>() {

    lateinit var onAddClick : (String) -> Unit
    lateinit var onRemoveClick : (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sort_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.chip.text = item

        holder.chip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) onAddClick(item)
            else onRemoveClick(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chip: Chip = view.findViewById(R.id.filterChip)
    }
}