package com.jk.taskmanager.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jk.taskmanager.data.local.Pref
import com.jk.taskmanager.databinding.FragmentOnBoardingBinding
import com.jk.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter{
            pref.saveShowBoarding(true)

            findNavController().navigateUp()
        }

        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)


    }

    private fun onClick(view: View) {
        findNavController().navigateUp()
    }
}