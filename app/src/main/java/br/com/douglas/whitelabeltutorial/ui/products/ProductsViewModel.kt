package br.com.douglas.whitelabeltutorial.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglas.whitelabeltutorial.config.Config
import br.com.douglas.whitelabeltutorial.domain.model.Product
import br.com.douglas.whitelabeltutorial.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    config: Config
) : ViewModel() {

    private val _productsData = MutableLiveData<List<Product>>()
    val productsData : LiveData<List<Product>> = _productsData

    private val _addProductVisibilityData = MutableLiveData(config.addButtonVisibility)
    val addProductVisibilityData : LiveData<Int> = _addProductVisibilityData

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductsUseCase()
            _productsData.value = products
        } catch (e: Exception) {
            Log.d("ProductsViewModel", e.toString())
        }
    }

}