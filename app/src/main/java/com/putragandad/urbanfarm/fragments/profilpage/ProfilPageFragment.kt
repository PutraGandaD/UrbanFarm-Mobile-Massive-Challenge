package com.putragandad.urbanfarm.fragments.profilpage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.landingpage.LandingPageActivity
import com.putragandad.urbanfarm.activity.profilpage.ProfilEditPageActivity
import com.putragandad.urbanfarm.databinding.FragmentProfilPageBinding

class ProfilPageFragment : Fragment() {
    private var _binding : FragmentProfilPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilPageBinding.inflate(inflater, container, false)

        //get firebase instance
        auth = FirebaseAuth.getInstance()

        //get profil value from firebase
        val profileName = auth.currentUser?.displayName
        val profilePictureUrl = auth.currentUser?.photoUrl

        //set profile value from data above
        binding.tvProfileName.text = profileName
        Glide.with(requireContext())
            .load(profilePictureUrl)
            .into(binding.ivProfileImage)

        //logout
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireContext(), LandingPageActivity::class.java))
            requireActivity().finish()
        }

        binding.btnEditProfil.setOnClickListener {
            startActivity(Intent(requireContext(), ProfilEditPageActivity::class.java))
        }

        return binding.root
    }

}