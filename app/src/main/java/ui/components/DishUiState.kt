package ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
class DishViewModel(private val dao: DishDao) : ViewModel(){
    // holds the current state of the UI
    private val _uiState = MutableStateFlow(DishUiState())
    // what the UI is reading
    val uiState: StateFlow<DishUiState> = _uiState.asStateFlow()
    val moods = listOf("week night", "spicy", "savory", "comfort food", "sweet", "high protein", "date night")

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
        val dishes = _uiState.value.dishes
        val filtered = dishes.filter{it.mood in selectedMoods}
        return if (filtered.isNotEmpty()) {
            filtered.random()
        } else {
            null
    }
}

class DishViewModelFactory(private val dao: DishDao) : ViewModelProvider.Factory {
    override fun<T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DishViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DishViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown viewmodel Class")
    }

    val moods = listOf("Lazy", "Pure Comfort", "Cozy And Warm", "Fresh Reset", "Midnight Craving", "Spicy", "Savory")
}