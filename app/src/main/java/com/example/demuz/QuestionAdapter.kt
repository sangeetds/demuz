package com.example.demuz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.Card>() {

    class Card(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var question: Question? = null
        private val cardBody = itemView.findViewById<LinearLayout>(R.id.questionBody)
        val questionName: TextView = cardBody.findViewById<TextView>(R.id.questionName)


        init {
            itemView.setOnClickListener(this)
        }
//
        override fun onClick(v: View) {

        }
//
        companion object {
            private const val PHOTO_KEY = "PHOTO"
        }
    }

    override fun getItemCount() = this.questions.size

    override fun onBindViewHolder(holder: Card, position: Int) {
        val questionItem = questions[position]
        holder.questionName.text = questionItem.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Card {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.questionlist_item, parent, false)
        return Card(inflatedView)
    }
}