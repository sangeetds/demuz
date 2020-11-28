package com.example.demuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionView = findViewById<RecyclerView>(R.id.questionList)
        questionView.setHasFixedSize(true)

        val questionList = getListOfNames()
        questionAdapter = QuestionAdapter(questionList)
        questionView.adapter = questionAdapter

        questionView.layoutManager = LinearLayoutManager(this)

        questionAdapter.itemClickListener = { position, name ->
            Toast.makeText(this, "position: $position - name: $name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getListOfNames(): List<Question> {
        val questionDao = QuestionDataBase.getDatabase(applicationContext)!!.questionDao()
        val questions = QuestionRepository(questionDao).allQuestions

        return questions + Question(id = 1, title = "Two sums", source = R.drawable.hr)
    }
}

//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val rclNames = findViewById<RecyclerView>(R.id.questionList)
//
//        // If size of the all items are equal and won't change for a better performance it's better to set setHasFixedSize to true
//        rclNames.setHasFixedSize(true)
//
//        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
//        val nameList =  getListOfNames()
//        val namesAdapter = QuestionAdapter(nameList)
//        rclNames.adapter = namesAdapter
//        // Setting our RecyclerView's layout manager equal to LinearLayoutManager
//        rclNames.layoutManager = LinearLayoutManager(this)
//
//    }
//
//    // This function just creates a list of names for us
//    private fun getListOfNames(): MutableList<String> {
//        val nameList = mutableListOf<String>()
//        nameList.add("Ali")
//        nameList.add("Sophia")
//        nameList.add("Isabella")
//        nameList.add("Mason")
//        nameList.add("Jacob")
//        nameList.add("William")
//        nameList.add("Olivia")
//        nameList.add("Jayden")
//        nameList.add("Chloe")
//        nameList.add("Ella")
//        nameList.add("Anthony")
//        nameList.add("Joshua")
//        nameList.add("James")
//        nameList.add("Grace")
//        nameList.add("Samantha")
//        nameList.add("Nicholas")
//        nameList.add("Brianna")
//        nameList.add("Justin")
//        nameList.add("Lauren")
//        nameList.add("Kimberly")
//
//        return nameList
//    }
//}