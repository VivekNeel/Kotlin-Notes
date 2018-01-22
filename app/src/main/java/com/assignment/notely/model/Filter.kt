package com.assignment.notely.model

/**
 * Created by vivek on 22/01/18.
 */
object Filter {

    var markedStar: Boolean = false
    var markedFav: Boolean = false

    fun clearFilters() {
        markedStar = false
        markedFav = false
    }
}