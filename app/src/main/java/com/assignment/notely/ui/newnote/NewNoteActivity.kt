package com.assignment.notely.ui.newnote

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.assignment.notely.NotelyApplication
import com.assignment.notely.R
import com.assignment.notely.databinding.ActivityNewNoteBinding
import com.assignment.notely.ui.BaseActivity
import com.assignment.notely.ui.viewnote.ViewNoteActivity

/**
 * Created by vivek on 21/01/18.
 */
class NewNoteActivity : BaseActivity(), () -> Unit {
    override fun invoke() {
        startActivity(Intent(this, ViewNoteActivity::class.java))
    }

    lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)
        var id = intent.getIntExtra("noteId", -1)
        setActionBar(binding.editToolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.model = NewNoteViewModel(this, application as NotelyApplication, id)

//        binding.model.getFav().observe(this, Observer {
//            if (it != null) {
//                var list = it
//
//            }
//        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.saveNote -> {
                binding.model.insertNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}