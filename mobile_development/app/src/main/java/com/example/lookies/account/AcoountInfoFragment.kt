package com.example.lookies.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lookies.main.UserPreference
import com.example.lookies.main.ViewModelFactory
import com.example.lookies.databinding.FragmentAcoountInfoBinding
import com.example.lookies.login.SignInActivity
import com.example.lookies.login.SignInViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

class AcoountInfo : Fragment() {

    private var _binding: FragmentAcoountInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAcoountInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
        )[SignInViewModel::class.java]
        binding.logoutFab.setOnClickListener {
            viewModel.userLogout()
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            activity?.finish()
        }
    }
}