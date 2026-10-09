package data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Dish::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(context, AppDatabase::class.java, "dish_db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val currentInstance = instance
                            if (currentInstance != null) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    currentInstance.dishDao().insertAll(Dish.defaultDishes())
                                }
                            }
                        }
                    })
                    .build()
                instance = database
                database
            }
        }
    }
}