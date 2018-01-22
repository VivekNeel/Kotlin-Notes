package com.assignment.notely.ui.detail

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.assignment.notely.R
import com.assignment.notely.databinding.ActivityDetailViewNoteBinding
import com.assignment.notely.ui.newnote.NewNoteActivity
import kotlinx.android.synthetic.main.activity_detail_view_note.*

/**
 * Created by vivek on 21/01/18.
 */
class DetailViewActivity : AppCompatActivity(), LifecycleRegistryOwner {

    lateinit var binding: ActivityDetailViewNoteBinding
    internal var id: Int = 0

    private val mRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_view_note)
        id = intent.getIntExtra("noteId", -1)
        binding.model = DetailViewModel(application, id)
        setSupportActionBar(viewToolbar)
        with(supportActionBar!!) {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.editNote -> {
                val intent = Intent(this, NewNoteActivity::class.java)
                intent.putExtra("noteId", this.id)
                this.startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}