package com.example.demuz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class QuestionAdapter(private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.Card>(), Filterable {

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null
    private var filteredQuestions: List<Question> = questions

    inner class Card(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var question: Question? = null
        val questionName: TextView = itemView.findViewById(R.id.questionTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

        }
    }

    override fun getItemCount() = this.filteredQuestions.size

    override fun onBindViewHolder(holder: Card, position: Int) {
        val questionItem = filteredQuestions[position]
        holder.questionName.text = questionItem.title

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, questionItem.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Card {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.questionlist_item, parent, false)
        return Card(inflatedView)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults? {
                val charString = constraint.toString()

                filteredQuestions = if (charString.isEmpty()) {
                    questions
                } else {
                    questions.filter { it.title.contains(constraint, true) }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredQuestions
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredQuestions = filterResults.values as List<Question>
                notifyDataSetChanged()
            }
        }
    }
}

