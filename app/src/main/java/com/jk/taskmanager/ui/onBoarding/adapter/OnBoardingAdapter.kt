package com.jk.taskmanager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.jk.taskmanager.R
import com.jk.taskmanager.databinding.ItemOnBoardingBinding
import com.jk.taskmanager.model.OnBoard
import kotlin.reflect.KFunction1

class OnBoardingAdapter(private val onClick:() -> Unit) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val arrayList = arrayListOf<OnBoard>(
        OnBoard(R.drawable.time, "Task Manager", "Creative space to save your time"),
        OnBoard(R.drawable.week, "Notifications", "Quick notes on the go"),
        OnBoard(R.drawable.qwerty, "Always with you", "Fully functional offline")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {

        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.apply {
                btnGetStarted.isVisible = adapterPosition == arrayList.lastIndex
                btnSkip.isVisible = adapterPosition != arrayList.lastIndex
                onBoard.image?.let { onBoardImgV.setImageResource(it) }
                tvTitle.text = onBoard.title
                tvDescription.text = onBoard.description
                binding.btnGetStarted.setOnClickListener {
                    onClick()
                }
                binding.btnSkip.setOnClickListener {
                    onClick()


                    }
                }
            }
        }
    }
