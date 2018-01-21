package com.assignment.notely.ui.viewnote

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.assignment.notely.R
import com.assignment.notely.databinding.ActivityViewnoteBinding
import com.assignment.notely.db.entities.Note
import com.assignment.notely.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_viewnote.*

class ViewNoteActivity : BaseActivity() {

    lateinit var adapter: ViewNoteAdapter
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        adapter = ViewNoteAdapter(application as Application, emptyList())

        noteViewModel.getNotes().observe(this, Observer {
            if (it != null)
                fillRecycler(it)
        })

        noteViewModel.getFav().observe(this, Observer {
            if (it != null) {
                var list = it
            }
        })


        val binding = DataBindingUtil.setContentView<ActivityViewnoteBinding>(this, R.layout.activity_viewnote)
        binding.viewModel = noteViewModel
        setActionBar(binding.toolbar)
        binding.toolbar.setTitleTextAppearance(this, R.style.CustomToolbarFont)
        notesRecycler.layoutManager = LinearLayoutManager(this)
        notesRecycler.adapter = adapter

    }

    private fun fillRecycler(list: List<Note>) {
        adapter.addItems(list)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.noteCreate -> noteViewModel.fabClick()
            R.id.noteFilter -> Log.d("main", "todo")
        }
        return super.onOptionsItemSelected(item)
    }
}
