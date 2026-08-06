package data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")

data class Dish (
    @PrimaryKey(autoGenerate = true) val dishId : Int = 0,
    @ColumnInfo(name = "dish_name") val dishName: String?,
    @ColumnInfo(name="mood") val mood: String?,
    @ColumnInfo(name="notes") val notes: String?,
    @ColumnInfo(name="image_path") val imagePath: String?

)