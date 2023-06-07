package com.program.nivelacion.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Resultados::class)], version = 1, exportSchema = false)
abstract class ResultadoRoomDatabase: RoomDatabase() {

    abstract fun resultadoDao(): ResultadoDao

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: ResultadoRoomDatabase? = null

        fun getInstance(context: Context): ResultadoRoomDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResultadoRoomDatabase::class.java,
                        "resultado_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}