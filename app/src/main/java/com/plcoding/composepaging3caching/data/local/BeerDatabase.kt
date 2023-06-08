package com.plcoding.composepaging3caching.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BeerEntity::class], version = 1)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun BeerDao(): BeerDao

    companion object {
        @Volatile
        private var Instance: BeerDatabase? = null

        fun getDatabase(context: Context): BeerDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BeerDatabase::class.java, "item_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}