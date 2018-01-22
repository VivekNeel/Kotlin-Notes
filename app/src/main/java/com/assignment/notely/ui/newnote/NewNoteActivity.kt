package com.assignment.notely.ui.newnote

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.assignment.notely.NotelyApplication
import com.assignment.notely.R
import com.assignment.notely.databinding.ActivityNewNoteBinding
import com.assignment.notely.ui.BaseActivity
import com.assignment.notely.ui.viewnote.ViewNoteActivity
import kotlinx.android.synthetic.main.activity_new_note.*

/**
 * Created by vivek on 21/01/18.
 */
class NewNoteActivity : AppCompatActivity(), () -> Unit, LifecycleRegistryOwner {
    override fun invoke() {
        startActivity(Intent(this, ViewNoteActivity::class.java))
    }

    lateinit var binding: ActivityNewNoteBinding

    private val mRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)
        var id = intent.getIntExtra("noteId", -1)
        setSupportActionBar(editToolbar)
        with(supportActionBar!!) {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
        binding.model = NewNoteViewModel(this, application as NotelyApplication, id)

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