package com.assignment.notely.ui.viewnote

import android.app.Application
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.notely.R
import com.assignment.notely.databinding.ItemNoteBinding
import com.assignment.notely.db.entities.Note

/**
 * Created by vivek on 21/01/18.
 */
class ViewNoteAdapter(private var app: Application, var notelist: List<Note>) : RecyclerView.Adapter<ViewNoteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val postBinding = DataBindingUtil.inflate<ItemNoteBinding>(LayoutInflater.from(parent.context), R.layout.item_note, parent, false)
        val viewModel = AdapterViewModel(app)
        postBinding.viewModel = viewModel
        return ViewHolder(postBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel.note = notelist[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = notelist.size

    class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    fun addItems(list: List<Note>) {
        notelist = list
        notifyDataSetChanged()
    }
}