package com.example.demuz

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demuz.Filters.*


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    lateinit var questionAdapter: QuestionAdapter
    private lateinit var questionRepository: QuestionRepository

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionRepository = QuestionRepository(QuestionDataBase.getDatabase(this)!!.questionDao())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Title"

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val filterButton = findViewById<Button>(R.id.filterButton)
        filterButton.setOnClickListener {
            showBottomSheetFilterFragment()
        }

        val questionView = findViewById<RecyclerView>(R.id.questionList)
        questionView.setHasFixedSize(true)

        val questionList = getListOfNames()
        questionAdapter = QuestionAdapter(this, questionList.toMutableList())
        questionView.adapter = questionAdapter
        questionView.layoutManager = LinearLayoutManager(this)
    }

    private fun getListOfNames() = questionRepository.allQuestions

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                questionAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                questionAdapter.filter.filter(query)
                return false
            }
        })

        return true
    }

    private fun showBottomSheetFilterFragment() {
        val bottomSheetFragment = FilterFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)

        bottomSheetFragment.onSubmit = { list, name ->
            println("$list $name")
            val newQuestionList: List<Question> = when (name) {
                COLLEGE -> list.fold(mutableSetOf<Question>()) { acc, item ->
                    acc.addAll(
                        questionRepository.filterCollege(
                            item
                        )
                    ); acc
                }.toMutableList()
                COMPANY -> list.fold(mutableSetOf<Question>()) { acc, item ->
                    acc.addAll(
                        questionRepository.filterCompany(
                            item
                        )
                    ); acc
                }.toMutableList()
                TOPICS -> list.fold(mutableSetOf<Question>()) { acc, item ->
                    acc.addAll(
                        questionRepository.filterTopics(
                            item
                        )
                    ); acc
                }.toMutableList()
                ROLE -> list.fold(mutableSetOf<Question>()) { acc, item ->
                    acc.addAll(
                        questionRepository.filterRole(
                            item
                        )
                    ); acc
                }.toMutableList()
                DIFFICULTY -> list.fold(mutableSetOf<Question>()) { acc, item ->
                    acc.addAll(
                        questionRepository.filterDifficulty(
                            item
                        )
                    ); acc
                }.toMutableList()
                FAVORITE -> questionRepository.favoriteQuestions.toMutableList()
                COMPLETED -> if (list.size == 1 && list.first() == "Completed") questionRepository.completedQuestions
                else if (list.size == 1 && list.first() == "Not Started") questionRepository.uncompletedQuestions
                else questionRepository.uncompletedQuestions + questionRepository.completedQuestions
                else -> questionRepository.allQuestions
            }

            println(newQuestionList)

            questionAdapter.filteredQuestions = newQuestionList.toMutableList()
            questionAdapter.notifyDataSetChanged()
        }
    }

    companion object {

        private var instance: MainActivity? = null

        private fun getQuestions() : List<Question> =
            QuestionRepository(
                QuestionDataBase.getDatabase(instance!!.applicationContext!!)!!.questionDao()
            ).allQuestions


        fun getAllCompanies(): List<String> = getQuestions().fold(mutableSetOf<String>()) { acc, question ->
            acc.addAll(question.companies.split(","))
            acc.forEach { it.trim() }
            acc
        }.toList()

        fun getAllRole(): List<String> = getQuestions().fold(mutableSetOf<String>()) { acc, question ->
            acc.addAll(question.role.split(","))
            acc.forEach { it.trim() }
            acc
        }.toList()

        fun getAllTopics(): List<String> = getQuestions().fold(mutableSetOf<String>()) { acc, question ->
            acc.addAll(question.topics.split(","))
            acc.forEach { it.trim() }
            acc
        }.toList()

        fun getAllColleges(): List<String> = getQuestions().fold(mutableSetOf<String>()) { acc, question ->
            acc.addAll(question.college.split(","))
            acc.forEach { it.trim() }
            acc
        }.toList()

        fun getAllDifficulty(): List<String> = getQuestions().fold(mutableSetOf<String>()) { acc, question ->
            acc.addAll(question.difficulty.split(","))
            acc.forEach { it.trim() }
            acc
        }.toList()

        fun getFavorites(): List<String> = listOf("Favorite")

        fun getCompleted(): List<String> = listOf("Completed", "Not Started")
    }
}