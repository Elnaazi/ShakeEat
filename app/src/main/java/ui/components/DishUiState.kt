package ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Dish
import data.DishDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DishUiState(
    val dishes: List<Dish> = emptyList(),
    val selectedMood: String = ""
)

class DishViewModel(private val dao: DishDao) : ViewModel() {
    private val _uiState = MutableStateFlow(DishUiState())
    val uiState: StateFlow<DishUiState> = _uiState.asStateFlow()
    val moods = listOf(
        "week night",
        "spicy",
        "savory",
        "comfort food",
        "sweet",
        "high protein",
        "date night"
    )

    init {
        viewModelScope.launch {
            dao.getAllDishes().collect { dishes ->
                _uiState.value = _uiState.value.copy(dishes = dishes)
            }
        }
    }

    fun insertDish(dish: Dish) {
        viewModelScope.launch {
            dao.insert(dish)
        }
    }

    fun deleteDish(dish: Dish) {
        viewModelScope.launch {
            dao.delete(dish)
        }
    }

    fun updateDish(dish: Dish) {
        viewModelScope.launch {
            dao.update(dish)
        }
    }

    fun getRandomDishByMoods(selectedMoods: List<String>): Dish? {
        val filtered = _uiState.value.dishes.filter { it.mood in selectedMoods }
        return filtered.takeIf { it.isNotEmpty() }?.random()
    }
}