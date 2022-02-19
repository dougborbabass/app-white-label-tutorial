package br.com.douglas.whitelabeltutorial.data

import android.net.Uri
import br.com.douglas.whitelabeltutorial.domain.model.Product

interface ProductDataSource {

    suspend fun getProducts(): List<Product>

    suspend fun uploadProductImage(imageUri: Uri): String

    suspend fun createProduct(product: Product): Product
}