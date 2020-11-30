package com.example.demuz

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class QuestionAdapter(
    private val context: Context?,
    private val questions: MutableList<Question>,
) : RecyclerView.Adapter<QuestionAdapter.Card>(), Filterable {

    var filteredQuestions: MutableList<Question> = questions

    inner class Card(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val questionName: TextView = itemView.findViewById(R.id.questionTitle)
        val tag: ChipGroup = itemView.findViewById(R.id.tag)

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

        val tags = mutableListOf<String>()
        questionItem.addTags(tags)

        tags.forEach {
            val chip = Chip(holder.tag.context)
            chip.text = it
            chip.isClickable = true
            chip.height = 40
            holder.tag.addView(chip)
        }

        holder.itemView.setOnClickListener {
            val detailIntent = Intent(context, QuestionDetailActivity::class.java)
            detailIntent.putExtra("Properties", questionItem)
            context!!.startActivity(detailIntent)
        }

        val codingButton = holder.itemView.findViewById<Button>(R.id.code)
        val favoriteButton = holder.itemView.findViewById<ToggleButton>(R.id.buttonFavorite)

        codingButton.setOnClickListener {
            val uri: Uri =
                Uri.parse(questionItem.url) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context!!.startActivity(intent)
        }

        favoriteButton.isChecked = questionItem.favorite
        favoriteButton.setOnClickListener {
            questionItem.favorite = !questionItem.favorite
            notifyDataSetChanged()
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
                val charString: String = constraint.toString()

                filteredQuestions = if (charString.isEmpty()) questions
                    else questions
                            .filter { it.title.contains(constraint, true) }
                            .toMutableList()

                val filterResults = FilterResults()
                filterResults.values = filteredQuestions

                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredQuestions = filterResults.values as MutableList<Question>
                notifyDataSetChanged()
            }
        }
    }

    private fun Question.addTags(tags: MutableList<String>) {
        tags.addAll(this.college.split(","))
        tags.addAll(this.companies.split(","))
        tags.addAll(this.topics.split(","))
        tags.add(this.role)
        tags.add(this.difficulty)
        tags.add(this.frequency.toString())
    }
}



