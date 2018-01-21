package com.assignment.notely.ui.newnote

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.assignment.notely.NotelyApplication
import com.assignment.notely.R
import com.assignment.notely.databinding.ActivityNewNoteBinding
import com.assignment.notely.ui.BaseActivity

/**
 * Created by vivek on 21/01/18.
 */
class NewNoteActivity : BaseActivity(), () -> Unit {
    override fun invoke() {
        finish()
    }

    lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)
        var id = intent.getIntExtra("noteId", -1)
        binding.model = NewNoteViewModel(this, application as NotelyApplication, id)

//        binding.model.getFav().observe(this, Observer {
//            if (it != null) {
//                var list = it
//
//            }
//        })

    }
}