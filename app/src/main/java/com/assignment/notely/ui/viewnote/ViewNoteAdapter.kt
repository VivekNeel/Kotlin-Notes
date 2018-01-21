package com.assignment.notely.ui.viewnote

import android.app.Application
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.notely.R
import com.assignment.notely.databinding.ItemNoteBinding
import com.assignment.notely.databinding.ItemNoteNewBinding
import com.assignment.notely.db.entities.Note
import kotlinx.android.synthetic.main.item_note_new.view.*
import android.content.ClipData.Item


/**
 * Created by vivek on 21/01/18.
 */
class ViewNoteAdapter(private var app: Application, var notelist: List<Note>) : RecyclerView.Adapter<ViewNoteAdapter.ViewHolder>() {


    lateinit var binding: ItemNoteNewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate<ItemNoteNewBinding>(LayoutInflater.from(parent.context), R.layout.item_note_new, parent, false)
        val viewModel = AdapterViewModel(app)
        binding.viewModel = viewModel
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel.note = notelist[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = notelist.size

    class ViewHolder(var binding: ItemNoteNewBinding) : RecyclerView.ViewHolder(binding.root) {
        var viewBackground = itemView.view_background
        val viewForeground = itemView.view_background
    }

    fun addItems(list: List<Note>) {
        notelist = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
//        notelist.remove(position)
//        // notify the item removed by position
//        // to perform recycler view delete animations
//        // NOTE: don't call notifyDataSetChanged()
//        notifyItemRemoved(position)
        binding.viewModel.deleteNote(notelist[position])

    }

    fun restoreItem(item: Item, position: Int) {
//        cartList.add(position, item)
//        // notify item added by position
//        notifyItemInserted(position)
    }
}