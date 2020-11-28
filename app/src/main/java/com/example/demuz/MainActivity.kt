package com.example.demuz

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.demuz.R.layout.activity_main)

        toolbar = findViewById(com.example.demuz.R.id.toolbar);
        setSupportActionBar(toolbar);

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(com.example.demuz.R.id.viewpager);
        setupViewPager(viewPager!!)

        tabLayout = findViewById(com.example.demuz.R.id.tabs);

        tabLayout!!.setupWithViewPager(viewPager);
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UncompletedFragment(), "Uncompleted")
        adapter.addFragment(CompletedFragment(), "Completed")
        adapter.addFragment(FavoriteFragment(), "Favorite")
        viewPager.adapter = adapter
    }

    class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(
        manager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
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

//        val questionView = findViewById<RecyclerView>(R.id.questionList)
//        questionView.setHasFixedSize(true)
//
//        val questionList = getListOfNames()
//        questionAdapter = QuestionAdapter(questionList)
//        questionView.adapter = questionAdapter
//
//        questionView.layoutManager = LinearLayoutManager(this)
//
//        questionAdapter.itemClickListener = { position, name ->
//            Toast.makeText(this, "position: $position - name: $name", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun getListOfNames(): List<Question> {
//        val questionDao = QuestionDataBase.getDatabase(applicationContext)!!.questionDao()
//        val questions = QuestionRepository(questionDao).allQuestions
//
//        return questions +
//                listOf(Question(id = 1, title = "Two sums"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 2, title = "Make new"),
//                Question(id = 3, title = "wreafsgdfgdgd"),
//                Question(id = 4, title = "adrgrdgdfg"),
//                Question(id = 5, title = "waht now"))
//    }
}