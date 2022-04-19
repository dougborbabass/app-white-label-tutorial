package br.com.douglas.whitelabeltutorial.data

import android.net.Uri
import br.com.douglas.whitelabeltutorial.domain.model.Product

//Funcoes suspensas vem do Courotines

//A abstracao aqui feita diz que: Quem implementar essa interface
//tem que fornecer uma implementacao concreta das funcoes abaixo
interface ProductDataSource {

    suspend fun getProducts(): List<Product>

    suspend fun uploadProductImage(imageUri: Uri): String

    suspend fun createProduct(product: Product): Product
}