package com.assignment.notely.ui.viewnote

import android.app.Application
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.assignment.notely.R
import com.assignment.notely.RecyclerItemTouchHelper
import com.assignment.notely.databinding.ActivityViewnoteBinding
import com.assignment.notely.db.entities.Note
import com.assignment.notely.hide
import com.assignment.notely.model.Filter
import com.assignment.notely.show
import kotlinx.android.synthetic.main.activity_viewnote.*
import kotlinx.android.synthetic.main.app_bar_default.*
import kotlinx.android.synthetic.main.layout_drawer.*


class ViewNoteActivity : AppCompatActivity(), LifecycleRegistryOwner, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private val mRegistry = LifecycleRegistry(this)
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityViewnoteBinding


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is NoteAdapter.ViewHolder) {
            noteAdapter.removeItem(viewHolder.adapterPosition)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initViewmodel()
        setupToolbar()
        setupRecyclerView()
        setupListeners()

    }


    private fun initViewmodel() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteAdapter = NoteAdapter(application as Application, mutableListOf())

        noteViewModel.getNotes().observe(this, Observer {
            if (it != null)
                populateList(it)
        })


        binding = DataBindingUtil.setContentView<ActivityViewnoteBinding>(this, R.layout.activity_viewnote)
        binding.viewModel = noteViewModel

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitleTextAppearance(this, R.style.CustomToolbarFont)


    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        notesRecycler.addItemDecoration(DividerItemDecoration(notesRecycler.context, layoutManager.orientation))
        notesRecycler.layoutManager = layoutManager
        notesRecycler.adapter = noteAdapter

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(notesRecycler)

    }

    private fun setupListeners() {
        fav.setOnClickListener {
            com.assignment.notely.model.Filter.markedFav = !com.assignment.notely.model.Filter.markedFav
            if (com.assignment.notely.model.Filter.markedFav) {
                fav.setTextColor(ContextCompat.getColor(this, R.color.colorTeal))
                fav.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_item_selected, 0)
            } else {
                fav.setTextColor(Color.WHITE)
                fav.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_item_not_selected, 0)
            }
        }

        starText.setOnClickListener {
            com.assignment.notely.model.Filter.markedStar = !com.assignment.notely.model.Filter.markedStar
            if (com.assignment.notely.model.Filter.markedStar) {
                starText.setTextColor(ContextCompat.getColor(this, R.color.colorTeal))
                starText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_item_selected, 0)
            } else {
                starText.setTextColor(Color.WHITE)
                starText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_item_not_selected, 0)

            }
        }

        filter.setOnClickListener {
           onFilterClearClicked()
        }
        applyButton.setOnClickListener {
            invalidateOptionsMenu()
            closeDrawer()
            with(com.assignment.notely.model.Filter) {
                if (markedFav && markedStar) {
                    noteViewModel.getFavAndStarredNotes().observe(this@ViewNoteActivity, Observer {
                        if (it != null) {
                            populateList(it)
                        }
                    })
                } else if (markedFav) {
                    noteViewModel.getFav().observe(this@ViewNoteActivity, Observer {
                        if (it != null) {
                            populateList(it)
                        }
                    })
                } else if (markedStar) {
                    noteViewModel.getStarred().observe(this@ViewNoteActivity, Observer {
                        if (it != null) {
                            populateList(it)
                        }
                    })


                } else {
                    noteViewModel.getNotes().observe(this@ViewNoteActivity, Observer {
                        if (it != null)
                            populateList(it)
                    })

                }
            }
        }

    }

    private fun onFilterClearClicked(){
        Filter.clearFilters()
        fav.setTextColor(Color.WHITE)
        fav.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_item_not_selected, 0)
        starText.setTextColor(Color.WHITE)
        starText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_item_not_selected, 0)
        invalidateOptionsMenu()
        closeDrawer()
        noteViewModel.getNotes().observe(this@ViewNoteActivity, Observer {
            if (it != null)
                populateList(it)
        })
    }

    private fun closeDrawer() {
        drawer_layout.closeDrawer(Gravity.RIGHT, true)
    }

    private fun populateList(list: MutableList<Note>) {
        if (list.isEmpty()) {
            emptyText.show()
        } else {
            emptyText.hide()
        }
        noteAdapter.addItems(list)
        noteAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val item = menu.findItem(R.id.noteFilter)
        if (item != null) {
            if (Filter.markedFav || Filter.markedStar) {
                item.setIcon(R.drawable.filter_selected)
            } else {
                item.setIcon(R.drawable.filter_not_selected)
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.noteCreate -> noteViewModel.fabClick()
            R.id.noteFilter -> {
                drawer_layout.openDrawer(Gravity.RIGHT, true)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }
}
