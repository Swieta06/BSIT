package com.swieta.bsit.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swieta.bsit.presentation.fragment.ContactFragment
import com.swieta.bsit.presentation.fragment.ProfileFragment
import com.swieta.bsit.presentation.fragment.TransactionFragment

class BsitFragmentPagerAdapter(
    fragment: FragmentActivity,
    private val bundle: List<Bundle?>
) : FragmentStateAdapter(
    fragment,
) {
    private val fragment = listOf(
        ::TransactionFragment,
        ::ContactFragment,
        ::ProfileFragment
    )

    override fun getItemCount() = fragment.size

    override fun createFragment(position: Int): Fragment {
        val fragmentName = this.fragment.getOrNull(position)
        val fragmentInstnce = fragmentName?.invoke() as? Fragment ?: Fragment()

        val bundle = this.bundle.getOrNull(position)
        fragmentInstnce.arguments = bundle
        return fragmentInstnce
    }
}