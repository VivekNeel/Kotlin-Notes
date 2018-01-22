package com.assignment.notely

import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.os.Build
import android.app.Activity
import android.graphics.Color
import android.view.View


/**
 * Created by vivek on 22/01/18.
 */
class ActivityUtil{
    companion object {
        fun setLightStatusBar(view: View, activity: Activity) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                var flags = view.getSystemUiVisibility()
              //  flags = flags or View.sys
                view.setSystemUiVisibility(flags)
                activity.window.statusBarColor = Color.BLACK
            }
        }
    }
}