package com.jk.taskmanager.ui.task

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jk.taskmanager.App
import com.jk.taskmanager.databinding.FragmentTaskBinding
import com.jk.taskmanager.data.model.Task
import kotlin.coroutines.coroutineContext

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
            title = binding.etTitle.editText?.text.toString(),
            description = binding.etDesc.editText?.text.toString()
        )
        App.dataBase.taskDao().insert(data)
        findNavController().navigateUp()
        }
    }