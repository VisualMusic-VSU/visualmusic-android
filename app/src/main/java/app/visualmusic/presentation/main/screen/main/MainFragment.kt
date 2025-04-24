package app.visualmusic.presentation.main.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.visualmusic.R
import app.visualmusic.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBtnListeners()
    }


    private fun setupBtnListeners() {
        with(binding) {
            generateBtn.setOnClickListener {
                findNavController().navigate(R.id.action_mainScreen_to_generationActivity)
            }
            startAuthorizationBtn.setOnClickListener {
                findNavController().navigate(R.id.action_mainScreen_to_authorizationActivity)
            }
        }
    }
}