package com.assignment.notely.ui.viewnote

import android.app.Application
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.notely.R
import com.assignment.notely.databinding.ItemNoteBinding
import com.assignment.notely.db.entities.Note
import kotlinx.android.synthetic.main.item_note.view.*


/**
 * Created by vivek on 21/01/18.
 */
class NoteAdapter(private var app: Application, private var notelist: MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    lateinit var binding: ItemNoteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate<ItemNoteBinding>(LayoutInflater.from(parent.context), R.layout.item_note, parent, false)
        val viewModel = AdapterViewModel(app)
        binding.viewModel = viewModel
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel.note = notelist[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = notelist.size

    class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val viewForeground = itemView.view_background
    }

    fun addItems(list: MutableList<Note>) {
        notelist = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        binding.viewModel.deleteNote(notelist[position])
        notelist.removeAt(position)
        notifyItemRemoved(position)


    }
}