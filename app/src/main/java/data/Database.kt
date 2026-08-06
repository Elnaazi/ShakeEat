package data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dish::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context,AppDatabase::class.java,"dish_db")
                    .build().also {instance = it}
            }
        }
    }
}