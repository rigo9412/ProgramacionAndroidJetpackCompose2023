package com.lanazirot.simonsays.localdb

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.lanazirot.simonsays.domain.dao.IPlayerDAO
import com.lanazirot.simonsays.domain.model.Player

@Database(
    entities = [Player::class], version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = PlayerDB.Migration1To2::class),
    ]
)
abstract class PlayerDB : RoomDatabase() {
    abstract fun playerDao(): IPlayerDAO

    @RenameTable(fromTableName = "Player", toTableName = "Top")
    class Migration1To2 : AutoMigrationSpec  { }


}