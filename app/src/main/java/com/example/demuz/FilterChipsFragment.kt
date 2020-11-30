package com.example.demuz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterChipsFragment(private val filters: List<String>, private val name: Filters) : BottomSheetDialogFragment() {

    lateinit var filterChipsAdapter: FilterChipsAdapter
    lateinit var onSubmit: (MutableList<String>, Filters) -> Unit
    private val selectedList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sort_category_list, container, false)

        val filtersList = view.findViewById<RecyclerView>(R.id.categoryItem)
        filtersList.setHasFixedSize(true)

        filterChipsAdapter = FilterChipsAdapter(filters)
        filtersList.adapter = filterChipsAdapter
        val layoutManager = FlexboxLayoutManager(context)
        filtersList.layoutManager = layoutManager
        layoutManager.flexDirection = FlexDirection.ROW

        filterChipsAdapter.onAddClick = { chipName ->
            selectedList.add(chipName)
        }

        filterChipsAdapter.onRemoveClick = { chipName ->
            selectedList.remove(chipName)
        }

        val filterButton = view.findViewById<Button>(R.id.doFilter)
        filterButton.setOnClickListener {
            onSubmit(selectedList, name)
            dismiss()
        }

        return view
    }

}