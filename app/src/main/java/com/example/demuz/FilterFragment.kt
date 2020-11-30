package com.example.demuz

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {

    lateinit var filterAdapter: FilterAdapter
    lateinit var onSubmit : (MutableList<String>, Filters) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        val filterView = view.findViewById<RecyclerView>(R.id.filterList)
        filterView.setHasFixedSize(true)

        val filterList = Question.filters
        filterAdapter = FilterAdapter(filterList)
        filterView.adapter = filterAdapter

        filterView.layoutManager = LinearLayoutManager(context)

        filterAdapter.startFrag = { fragment: BottomSheetDialogFragment ->
            fragment.show(fragmentManager!!, "")
            (fragment as FilterChipsFragment).onSubmit = { list, name ->
                onSubmit(list, name)
                dismiss()
            }
        }

        val resetButton = view.findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            onSubmit(mutableListOf(), Filters.EMPTY)
            dismiss()
        }

        return view
    }
}