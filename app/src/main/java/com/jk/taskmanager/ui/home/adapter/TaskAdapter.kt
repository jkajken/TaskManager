package com.jk.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jk.taskmanager.databinding.ItemTaskBinding
import com.jk.taskmanager.data.model.Task

class TaskAdapter() :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val tasks: ArrayList<Task> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }
    fun addTask(task: Task){
        tasks.add(0,task)
        notifyItemChanged(0)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTitle.text=task.title
            binding.tvDescription.text=task.description

        }
    }
}
