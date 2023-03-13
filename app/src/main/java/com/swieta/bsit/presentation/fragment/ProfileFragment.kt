package com.swieta.bsit.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.swieta.bsit.R
import com.swieta.bsit.databinding.FragmenProfileBinding
import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.presentation.fragment.viewModel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmenProfileBinding? = null
    private val binding get() = _binding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmenProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.getProfile()
    }

    private fun observeViewModel() {
        viewModel.profile.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
            initViewProfile(it)
        }
    }

    private fun initViewProfile(data: ProfileResponse) {
        binding?.ivProfile?.let {
            Glide
                .with(context ?: return)
                .load(data.imageUrl)
                .centerCrop()
                .into(it)

        }
        binding?.tvName?.text = data.name
        binding?.tvJoindate?.text = data.joinedDate
        binding?.tvStatus?.text = if (data.status == 1)
            getString(R.string.available_title) else getString(R.string.not_available_title)
        binding?.tvPhone?.text = data.noTelp
        binding?.cardLocation?.setOnClickListener {
            setLocation(data.lat.toString(),data.lng.toString())
        }

    }

    private fun setLocation(lat: String, lng: String) {
        val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,%20$lng")
        val intent = Intent(Intent.ACTION_VIEW,uri)
        context?.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val PROFILE = "profile_response"

        fun createInstace(data: ProfileResponse) = Bundle().apply {
            putParcelable(PROFILE, data)

        }
    }
}