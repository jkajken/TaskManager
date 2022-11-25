package com.jk.taskmanager.ui.home.adapter


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jk.taskmanager.App
import com.jk.taskmanager.databinding.ItemTaskBinding
import com.jk.taskmanager.data.model.Task

class TaskAdapter(val context: Context) :
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

    // fun addTask(task: Task) {
    //   tasks.add(0, task)
    // notifyItemChanged(0)

    fun addTasks(newTask: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(newTask)
        notifyDataSetChanged()

    }

    private fun deleteItem(task: Task) {
        tasks.remove(task)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description
            itemView.setOnLongClickListener {
                deleteTask(task)
                return@setOnLongClickListener true
            }

        }
    }

    fun deleteTask(task: Task) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete task?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialogInterface, it ->
            App.dataBase.taskDao().delete(task)
            deleteItem(task)
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialogInterface, it ->
            dialogInterface.cancel()
        }
        builder.show()
    }

}
