package com.assignment.notely.ui.viewnote

import android.app.Application
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.assignment.notely.R
import com.assignment.notely.databinding.ActivityViewnoteBinding
import com.assignment.notely.db.entities.Note
import com.assignment.notely.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_viewnote.*
import android.support.v7.widget.helper.ItemTouchHelper
import com.assignment.notely.RecyclerItemTouchHelper
import android.arch.lifecycle.LifecycleRegistry
import android.view.Gravity
import kotlinx.android.synthetic.main.app_bar_default.*
import kotlinx.android.synthetic.main.layout_drawer.*


class ViewNoteActivity : AppCompatActivity(), LifecycleRegistryOwner, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is ViewNoteAdapter.ViewHolder) {

            val deletedIndex = viewHolder.itemId

            // remove the item from recycler view
            adapter.removeItem(viewHolder.adapterPosition)


        }
    }

    private val mRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }

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
        setSupportActionBar(toolbar)
        toolbar.setTitleTextAppearance(this, R.style.CustomToolbarFont)
        val layoutManager = LinearLayoutManager(this)
        notesRecycler.addItemDecoration(DividerItemDecoration(notesRecycler.context, layoutManager.orientation))
        notesRecycler.layoutManager = layoutManager
        notesRecycler.adapter = adapter

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(notesRecycler)

        applyButton.setOnClickListener{drawer_layout.closeDrawer(Gravity.RIGHT , true)}


    }

    private fun fillRecycler(list: List<Note>) {
        adapter.addItems(list)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.noteCreate -> noteViewModel.fabClick()
            R.id.noteFilter -> drawer_layout.openDrawer(Gravity.RIGHT , true)
        }
        return super.onOptionsItemSelected(item)
    }
}
