package data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey(autoGenerate = true) val dishId: Int = 0,
    @ColumnInfo(name = "dish_name") val dishName: String = "",
    @ColumnInfo(name = "mood") val mood: String = "",
    @ColumnInfo(name = "notes") val notes: String? = null,
    @ColumnInfo(name = "image_path") val imagePath: String? = null
) : Serializable {
    companion object {
        private const val PACKAGE_NAME = "com.example.shakeeat"

        fun defaultDishes(): List<Dish> = listOf(
            Dish(
                dishName = "Spaghetti Bolognese",
                mood = "comfort food",
                notes = "Classic comfort dish",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_pasta"
            ),
            Dish(
                dishName = "Crispy Tacos",
                mood = "spicy",
                notes = "Big and bold flavor",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_tacos"
            ),
            Dish(
                dishName = "Garden Salad",
                mood = "savory",
                notes = "Fresh and cheerful",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_salad"
            ),
            Dish(
                dishName = "Veggie Burger",
                mood = "week night",
                notes = "Easy and filling",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_burger"
            ),
            Dish(
                dishName = "Sweet Pancakes",
                mood = "sweet",
                notes = "A fun treat",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_pancakes"
            ),
            Dish(
                dishName = "Protein Bowl",
                mood = "high protein",
                notes = "Balanced and energizing",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_bowl"
            ),
            Dish(
                dishName = "Margherita Pizza",
                mood = "date night",
                notes = "Sharing time",
                imagePath = "android.resource://$PACKAGE_NAME/drawable/food_pizza"
            )
        )
    }
}