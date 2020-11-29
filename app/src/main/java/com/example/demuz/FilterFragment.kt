package com.example.demuz

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        val filterView = view.findViewById<RecyclerView>(R.id.filterList)
        filterView.setHasFixedSize(true)

        val filterList = Question.filters
        val filterListAdapter = FilterListAdapter(filterList)
        filterView.adapter = filterListAdapter

        filterView.layoutManager = LinearLayoutManager(context)

        return view
    }

    companion object {
        const val TAG = "ModalBottomSheetFilter"
    }
}