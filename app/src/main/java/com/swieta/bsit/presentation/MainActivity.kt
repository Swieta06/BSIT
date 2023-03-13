package com.swieta.bsit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.swieta.bsit.R
import com.swieta.bsit.databinding.ActivityMainBinding
import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.model.TransactionResponse
import com.swieta.bsit.presentation.adapter.BsitFragmentPagerAdapter
import com.swieta.bsit.presentation.adapter.TransactionAdapter
import com.swieta.bsit.presentation.adapter.ViewPagerAdapter
import com.swieta.bsit.presentation.fragment.ContactFragment
import com.swieta.bsit.presentation.fragment.ProfileFragment
import com.swieta.bsit.presentation.fragment.TransactionFragment
import com.swieta.bsit.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

//    private lateinit var viewPagerAdapter:ViewPagerAdapter

//    private val transactionAdapter = TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager(listOf())
        setUpTabLayout()
        observeViewModel()
        mainViewModel.getTransaction()
        mainViewModel.getContact()
        mainViewModel.getProfile()

    }
//    private  fun setData(dataTransaction:List<TransactionResponse>,dataContact:List<ContactResponse>,dataProfile: ProfileResponse?){
//        val bundle= listOf(
//            dataContact.let {
//                ContactFragment.createInstace(it) },
//            dataProfile.let {
//                it?.let { it1 -> ProfileFragment.createInstace(it1) }
//            },
//            dataTransaction.let {
//                TransactionFragment.createInstace(it)}
//
//        )
//        setUpViewPager(bundle)
//    }

    private fun setUpViewPager(bundle: List<Bundle?>) {
        val viewPager = binding.vpContainer
        viewPager.adapter = BsitFragmentPagerAdapter(this, bundle)


    }

    private fun setUpTabLayout() {
        val tableLayout = binding.tabsFragment
        val viewPager = binding.vpContainer
        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Transaction"
                1 -> tab.text = "Contact"
                2 -> tab.text = "Profile"
            }

        }.attach()
    }

    private fun observeViewModel() {
        mainViewModel.transaction.observe(this) {
            //          setData(it, listOf(), null)
            setDataTransaction(it)
//            Toast.makeText(applicationContext, it[0].name, Toast.LENGTH_LONG).show()
        }
        mainViewModel.contact.observe(this) {
            //          setData(listOf(), it,null)
//            setDataTransaction(it)
//            Toast.makeText(applicationContext, it[0].name, Toast.LENGTH_LONG).show()
        }
        mainViewModel.profile.observe(this) {
            //           setData(listOf(), listOf(),it)
//            setDataTransaction(it)
//            Toast.makeText(applicationContext, it[0].name, Toast.LENGTH_LONG).show()
        }
        mainViewModel.errorMassage.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()

        }
        mainViewModel.showLoading.observe(this) { isShow ->
            if (isShow) showLoading() else hideLoading()


        }
    }

    private fun setDataTransaction(data: List<TransactionResponse>) {
        val adapter = TransactionAdapter(data)
//        binding.rvListTransaction.adapter = adapter

    }

    private fun showLoading() {
        binding.cmpLoading.root.visibility = View.VISIBLE
//        binding.rvListTransaction.visibility = View.GONE

    }

    private fun hideLoading() {

        binding.cmpLoading.root.visibility = View.GONE
        //binding.rvListTransaction.visibility = View.VISIBLE
    }
}