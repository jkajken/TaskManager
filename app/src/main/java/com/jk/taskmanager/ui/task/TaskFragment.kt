package com.jk.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jk.taskmanager.App
import com.jk.taskmanager.databinding.FragmentTaskBinding
import com.jk.taskmanager.data.model.Task

class
TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val value = it.getSerializable("task")
            if (value != null) {
                task = value as Task
                binding.etTitle.editText?.setText(task?.title.toString())
                binding.etDesc.editText?.setText(task?.description.toString())
                if (task != null) {
                    binding.saveBtn.text = "Update"
                } else {
                    binding.saveBtn.text = "Save"
                }
            }
        }
        binding.saveBtn.setOnClickListener {
            if (binding.etTitle.editText?.text.toString().isNotEmpty()) {
                if (task!=null){
               updateTask()
                }else saveTask()
            } else {
                binding.etTitle.error = "Fill the field"
            }
        }
    }

    private fun updateTask() {
       task?.title=binding.etTitle.editText?.text.toString()
       task?.description=binding.etDesc.editText?.text.toString()
        task?.let { App.dataBase.taskDao().update(it) }
        findNavController().navigateUp()
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