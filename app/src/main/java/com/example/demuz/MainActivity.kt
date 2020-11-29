package com.example.demuz

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var currentFragment: Fragment? = null

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

        viewPager = findViewById(com.example.demuz.R.id.viewpager)
        setupViewPager(viewPager!!)

        tabLayout = findViewById(com.example.demuz.R.id.tabs)

        tabLayout!!.setupWithViewPager(viewPager)

        val sortButton = findViewById<Button>(com.example.demuz.R.id.sortButton)
        val filterButton = findViewById<Button>(com.example.demuz.R.id.filterButton)

        sortButton.setOnClickListener {
            showBottomSheetSortFragment()
        }

        filterButton.setOnClickListener {
            showBottomSheetFilterFragment()
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UncompletedFragment(), "Uncompleted")
        adapter.addFragment(CompletedFragment(), "Completed")
        adapter.addFragment(FavoriteFragment(), "Favorite")
        viewPager.adapter = adapter
    }

    inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(
        manager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            currentFragment = mFragmentList[position]
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.example.demuz.R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(com.example.demuz.R.id.action_search)
            .actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                when(currentFragment!!::class.java) {
                    CompletedFragment::class.java -> {
                        val frag = currentFragment as CompletedFragment
                        frag.questionAdapter.filter.filter(query)
                    }
                    UncompletedFragment::class.java -> {
                        val frag = currentFragment as UncompletedFragment
                        frag.questionAdapter.filter.filter(query)
                    }
                    FavoriteFragment::class.java -> {
                        val frag = currentFragment as FavoriteFragment
                        frag.questionAdapter.filter.filter(query)
                    }
                }

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                when(currentFragment!!::class.java) {
                    CompletedFragment::class.java -> {
                        val frag = currentFragment as CompletedFragment
                        frag.questionAdapter.filter.filter(query)
                    }
                    UncompletedFragment::class.java -> {
                        val frag = currentFragment as UncompletedFragment
                        frag.questionAdapter.filter.filter(query)
                    }
                    FavoriteFragment::class.java -> {
                        val frag = currentFragment as FavoriteFragment
                        frag.questionAdapter.filter.filter(query)
                    }
                }

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
            QuestionRepository(QuestionDataBase.getDatabase(instance!!.applicationContext!!)!!.questionDao()).allQuestions


        fun getAllCompanies(): List<String> = getQuestions().map { it.companies }

        fun getAllRole(): List<String> = getQuestions().map { it.role }

        fun getAllTopics(): List<String> = getQuestions().map { it.topics }

        fun getAllColleges(): List<String> = getQuestions().map { it.college }
    }
}