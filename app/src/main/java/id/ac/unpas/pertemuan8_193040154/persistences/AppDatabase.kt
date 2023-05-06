package id.ac.unpas.pertemuan8_193040154.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pertemuan8_193040154.model.SetoranSampah
import id.ac.unpas.pertemuan8_193040154.persistences.SetoranSampahDao

@Database(entities = [SetoranSampah::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun setoranSampahDao(): SetoranSampahDao
}