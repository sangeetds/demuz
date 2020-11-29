package com.example.demuz

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val context: Context?, private val questions: MutableList<Question>) : RecyclerView.Adapter<QuestionAdapter.Card>(), Filterable {

    private var filteredQuestions: List<Question> = questions

    inner class Card(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val questionName: TextView = itemView.findViewById(R.id.questionTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }

    override fun getItemCount() = this.filteredQuestions.size

    override fun onBindViewHolder(holder: Card, position: Int) {
        val questionItem = filteredQuestions[position]
        holder.questionName.text = questionItem.title

        holder.itemView.setOnClickListener {
            val detailIntent = Intent(context, QuestionDetailActivity::class.java)
            detailIntent.putExtra("Properties", questionItem)
            context!!.startActivity(detailIntent)
        }

        val codingButton = holder.itemView.findViewById<Button>(R.id.code)
        val doneButton = holder.itemView.findViewById<Button>(R.id.done)
        val favoriteButton = holder.itemView.findViewById<Button>(R.id.buttonFavorite)

        codingButton.setOnClickListener {
            val uri: Uri =
                Uri.parse(questionItem.url) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context!!.startActivity(intent)
        }

        doneButton.visibility = if (questionItem.completed) View.GONE else View.VISIBLE
        doneButton.setOnClickListener {
            questionItem.completed = true
            removeItem(holder)
        }

        favoriteButton.setOnClickListener {
            questionItem.favorite = true
        }
    }

    private fun removeItem(holder: Card) {
        val actualPosition: Int = holder.adapterPosition
        questions.removeAt(actualPosition)
        notifyItemRemoved(actualPosition)
        notifyItemRangeChanged(actualPosition, questions.size)
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

