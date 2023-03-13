package com.swieta.bsit.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.swieta.bsit.databinding.FragmenContactBinding
import com.swieta.bsit.databinding.FragmenTransactionBinding
import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.presentation.adapter.ContactAdapter
import com.swieta.bsit.presentation.fragment.viewModel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {
    private var _binding: FragmenContactBinding? = null
    private val binding get() = _binding!!

    private var contacAdapter = ContactAdapter()
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmenContactBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getContact()
        observeViewModel()
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val editTextValue = binding.etSearch.text
                if (editTextValue.isEmpty()) {
                    binding.ivCancle.visibility = View.INVISIBLE
                } else {
                    binding.ivCancle.visibility = View.VISIBLE
                }
                viewModel.filterSearchContact(p0.toString())
            }
        })
        binding.ivCancle.setOnClickListener {
            binding.etSearch.setText("")
        }
    }


    private fun observeViewModel() {
        viewModel.contact.observe(viewLifecycleOwner) { contactData ->
            if (contactData != null) {
                setData(contactData)

            }

        }
    }

    private fun setData(data: List<ContactResponse>) {
        contacAdapter.setData(data.toMutableList())
        contacAdapter.onClickCall {
            callAfriend(it.noTelp)
        }
        binding?.rvContact?.adapter = contacAdapter

    }

    private fun callAfriend(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val CONTACT_RESPONSE = "contact_response"

        fun createInstace(data: List<ContactResponse>) = Bundle().apply {
            putParcelableArray(CONTACT_RESPONSE, data.toTypedArray())

        }
    }
}