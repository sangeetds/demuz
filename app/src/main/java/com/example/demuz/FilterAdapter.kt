package com.example.demuz

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.demuz.Filters.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterAdapter(
    private val values: List<Filters>
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    lateinit var startFrag: (BottomSheetDialogFragment) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_filter_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = values[position]
        holder.idView.text = filter.toString()

        holder.itemView.setOnClickListener {
            val fragment: BottomSheetDialogFragment =
                when (position) {
                    0 -> FilterChipsFragment(ArrayList(MainActivity.getAllColleges()), COLLEGE)
                    1 -> FilterChipsFragment(ArrayList(MainActivity.getAllCompanies()), COMPANY)
                    2 -> FilterChipsFragment( ArrayList(MainActivity.getAllTopics()), TOPICS)
                    3 -> FilterChipsFragment(ArrayList(MainActivity.getAllRole()), ROLE)
                    4 -> FilterChipsFragment(ArrayList(MainActivity.getAllDifficulty()), DIFFICULTY)
                    5 -> FilterChipsFragment(ArrayList(MainActivity.getCompleted()), COMPLETED)
                    else -> FilterChipsFragment(ArrayList(MainActivity.getFavorites()), FAVORITE)
                }

            startFrag(fragment)
        }
    }

    override fun getItemCount(): Int = this.values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.filterField)
    }
}