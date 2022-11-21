package com.jk.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.jk.taskmanager.R
import com.jk.taskmanager.databinding.FragmentTaskBinding
import com.jk.taskmanager.data.model.Task

class
TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveBtn.setOnClickListener {
            if (binding.etTitle.editText?.text.toString().isNotEmpty()) {
                saveTask()
            } else {
                binding.etTitle.error = "Fill the field"
            }
        }
    }

    private fun saveTask() {
        val data = Task(
            binding.etTitle.editText?.text.toString(),
            binding.etDesc.editText?.text.toString())
        setFragmentResult(
            "fr_task", bundleOf(
                "task" to data)
        )
        findNavController().navigateUp()
    }
}