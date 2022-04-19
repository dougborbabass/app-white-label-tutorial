package br.com.douglas.whitelabeltutorial.data

import android.net.Uri
import br.com.douglas.whitelabeltutorial.domain.model.Product
import javax.inject.Inject

//O Repositorio depende de uma abstracao e nao de uma implementacao concreta
class ProductRepository @Inject constructor(
    private val dataSource: ProductDataSource) {

    //Para cada um dos casos de uso, devemos ter uma funcao no repositorio
    //O repositorio existe por conta dos casos de uso

    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun uploadProductImage(imageUri: Uri): String = dataSource.uploadProductImage(imageUri)

    suspend fun createProduct(product: Product): Product = dataSource.createProduct(product)

}