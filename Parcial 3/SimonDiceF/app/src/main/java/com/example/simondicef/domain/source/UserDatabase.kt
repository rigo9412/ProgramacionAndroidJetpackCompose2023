package com.example.simondicef.domain.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simondicef.domain.dao.UserDao
import com.example.simondicef.domain.models.User

@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){
    abstract  fun userDao(): UserDao
}