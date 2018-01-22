package com.assignment.notely.db.entities

/**
 * Created by vivek on 21/01/18.
 */
class RoomConfig {

    companion object {
        const val NAME_DB: String = "notly.db"
        const val NAME_TABLE_NOTE: String = "note"
        const val QUERY_NOTE_ALL: String = "SELECT * FROM $NAME_TABLE_NOTE order by createdAt desc"
        const val QUERY_SINGLE_NOTE: String = "SELECT * FROM $NAME_TABLE_NOTE where id = :noteId"
        const val QUERY_NOTE_FAV: String = "SELECT * FROM $NAME_TABLE_NOTE where markedFav"
        const val QUERY_NOTE_STARRED: String = "SELECT * FROM $NAME_TABLE_NOTE where markedStar"
        const val QUERY_NOTE_STARRED_FAV: String = "SELECT * FROM $NAME_TABLE_NOTE where markedStar AND markedFav"

    }
}