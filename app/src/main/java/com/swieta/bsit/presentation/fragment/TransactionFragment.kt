package com.swieta.bsit.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.swieta.bsit.databinding.FragmenTransactionBinding
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.model.TransactionResponse

class TransactionFragment : Fragment() {
    private var _binding: FragmenTransactionBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmenTransactionBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object{
        const val TRANSACTION="transaction_response"

        fun createInstace(data: List<TransactionResponse>)=Bundle().apply{
            putParcelableArray(TRANSACTION,data.toTypedArray())

        }
    }
}