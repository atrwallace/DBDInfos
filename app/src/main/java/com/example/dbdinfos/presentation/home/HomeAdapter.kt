package com.example.dbdinfos.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmentlist = ArrayList<Fragment>()
    private val mFragmentTitlelist = ArrayList<String>()
    override fun getCount(): Int {

        return mFragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentlist[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitlelist[position]
    }

    fun addFrag(frag: Fragment, title: String) {
        mFragmentlist.add(frag)
        mFragmentTitlelist.add(title)

    }
}