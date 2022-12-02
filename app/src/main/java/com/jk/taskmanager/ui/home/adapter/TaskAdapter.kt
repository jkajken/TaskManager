package com.jk.taskmanager.ui.home.adapter


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jk.taskmanager.App
import com.jk.taskmanager.R
import com.jk.taskmanager.databinding.ItemTaskBinding
import com.jk.taskmanager.data.model.Task

class TaskAdapter(val context: Context, private val onClick:(Task) -> Unit) :
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
            if(adapterPosition % 2 == 0){
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context, R.color.white))
            }else{
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context, R.color.black))

            }
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description
            binding.root.setOnClickListener{
                onClick(task)
            }
            itemView.setOnLongClickListener {
                deleteTask(task)
                return@setOnLongClickListener true
            }

        }
    }

    fun deleteTask(task: Task) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete the task?")
        builder.setPositiveButton("Yes") { dialogInterface, _ ->
            App.dataBase.taskDao().delete(task)
            deleteItem(task)
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.cancel()
        }
        builder.show()
    }

}
