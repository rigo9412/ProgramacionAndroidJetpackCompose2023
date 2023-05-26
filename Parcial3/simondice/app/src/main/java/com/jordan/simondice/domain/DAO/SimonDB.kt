package com.jordan.simondice.domain.DAO

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.jordan.simondice.domain.models.Player

@Database(
    entities = [Player::class], version = 1, autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = SimonDB.PlayerAutoMigration::class),
        AutoMigration(from = 2, to = 3, spec = SimonDB.PlayerAutoMigration2::class)
    ], exportSchema = true
)
abstract class SimonDB : RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    @RenameTable(fromTableName = "Player", toTableName = "top")
    class PlayerAutoMigration : AutoMigrationSpec {

    }

    @RenameTable(fromTableName = "top", toTableName = "Player")
    class PlayerAutoMigration2 : AutoMigrationSpec {

    }



}