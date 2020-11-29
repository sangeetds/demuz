package com.example.demuz

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A fragment representing a list of Items.
 */
class SortListFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sort, container, false)

        val sortView = view.findViewById<RecyclerView>(R.id.sortList)
        sortView.setHasFixedSize(true)

        val sortList = Question.sort
        val sortListAdapter = FilterListAdapter(sortList)
        sortView.adapter = sortListAdapter

        sortView.layoutManager = LinearLayoutManager(context)

        return view
    }

    companion object {
        const val TAG = "ModalBottomSheetSort"
    }
}