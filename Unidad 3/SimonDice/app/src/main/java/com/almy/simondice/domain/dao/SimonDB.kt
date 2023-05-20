package com.almy.simondice.domain.dao

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.almy.simondice.domain.models.Player


@Database(

    entities = [Player::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = SimonDB.PlayerAutoMigrations::class),
         //       AutoMigration (from = 2, to = 3, spec = SimonDB.PlayerAutoMigrations

],
    exportSchema = true
)


abstract class SimonDB: RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    @RenameTable(fromTableName = "Player", toTableName = "top")
    class PlayerAutoMigration: AutoMigrationSpec{

    }

}