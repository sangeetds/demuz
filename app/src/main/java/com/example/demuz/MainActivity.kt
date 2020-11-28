package com.example.demuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionList = getListOfNames()

        linearLayoutManager = LinearLayoutManager(this)

        val questionView = findViewById<RecyclerView>(R.id.questionList)
        questionView.setHasFixedSize(true)
        questionView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        adapter = QuestionAdapter(questionList)
        questionView.adapter = adapter
    }

    private fun getListOfNames(): MutableList<Question> {
        val nameList = mutableListOf<Question>()
        nameList.add(Question("Ali"))
        nameList.add(Question("Sophia"))
        nameList.add(Question("Isabella"))
        nameList.add(Question("Mason"))
        nameList.add(Question("Jacob"))
        nameList.add(Question("William"))
        nameList.add(Question("Olivia"))
        nameList.add(Question("Jayden"))
        nameList.add(Question("Chloe"))
        nameList.add(Question("Ella"))
        nameList.add(Question("Anthony"))
        nameList.add(Question("Joshua"))
        nameList.add(Question("James"))
        nameList.add(Question("Grace"))
        nameList.add(Question("Samantha"))
        nameList.add(Question("Nicholas"))
        nameList.add(Question("Brianna"))
        nameList.add(Question("Justin"))
        nameList.add(Question("Lauren"))
        nameList.add(Question("Kimberly"))

        return nameList
    }
}