package com.example.demuz

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    lateinit var questionAdapter: QuestionAdapter
    private var searchView: SearchView? = null

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.demuz.R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Title"

        toolbar = findViewById(com.example.demuz.R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sortButton = findViewById<Button>(com.example.demuz.R.id.sortButton)
        val filterButton = findViewById<Button>(com.example.demuz.R.id.filterButton)

        sortButton.setOnClickListener {
            showBottomSheetSortFragment()
        }

        filterButton.setOnClickListener {
            showBottomSheetFilterFragment()
        }

        val questionView = findViewById<RecyclerView>(R.id.questionList)
        questionView.setHasFixedSize(true)

        val questionList = getListOfNames(this)
        questionAdapter = QuestionAdapter(this, questionList.toMutableList())
        questionView.adapter = questionAdapter
        questionView.itemAnimator = DefaultItemAnimator()
        questionView.layoutManager = LinearLayoutManager(this)
    }

    private fun getListOfNames(context: Context?): List<Question> {
        val questionDao = QuestionDataBase.getDatabase(context!!)!!.questionDao()

        return QuestionRepository(questionDao).allQuestions
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
    }

    private fun showBottomSheetSortFragment() {
        val sortListFragment = SortListFragment()
        sortListFragment.show(supportFragmentManager, sortListFragment.tag)
    }

    companion object {

        private var instance: MainActivity? = null

        fun getQuestions() : List<Question> =
            QuestionRepository(
                QuestionDataBase.getDatabase(instance!!.applicationContext!!)!!.questionDao()
            ).allQuestions


        fun getAllCompanies(): List<String> = getQuestions().map { it.companies }

        fun getAllRole(): List<String> = getQuestions().map { it.role }

        fun getAllTopics(): List<String> = getQuestions().map { it.topics }

        fun getAllColleges(): List<String> = getQuestions().map { it.college }
    }
}