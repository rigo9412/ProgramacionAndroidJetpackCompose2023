package com.almy.mochiapp.models

data class Assigment(
    val id: String? = "",
    val titleAssigment: String? = "",
    val createdBy: String? = "",
    val activities: List<Activity>? = listOf(),
    var progress: Int? = 0,
    val members: List<String>? = listOf(),
    var notes: MutableList<String>? = mutableListOf()
){
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to this.id,
            "titleAssigment" to this.titleAssigment,
            "createdBy" to this.createdBy,
            "activities" to this.activities,
            "progress" to this.progress,
            "members" to this.members,
            "notes" to this.notes
        )
    }
}

data class Activity(
    val id: String? = "",
    val nameActivity: String? = "",
    var done: Boolean? = false
){
    fun toMap(): MutableMap<String, Any?>{
        return mutableMapOf(
            "id" to this.id,
            "nameActivity" to this.nameActivity,
            "done" to this.done
        )
    }
}
