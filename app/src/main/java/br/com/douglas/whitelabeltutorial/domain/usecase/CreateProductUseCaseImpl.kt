package br.com.douglas.whitelabeltutorial.domain.usecase

import android.net.Uri
import br.com.douglas.whitelabeltutorial.data.ProductRepository
import br.com.douglas.whitelabeltutorial.domain.model.Product
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class CreateProductUseCaseImpl @Inject constructor(
    private val uploadProductImageUseCase: UploadProductImageUseCase,
    private val productRepository: ProductRepository
) : CreateProductUseCase {

    override suspend fun invoke(description: String, price: Double, imageUri: Uri): Product {
        return try {
            val imagUrl = uploadProductImageUseCase.invoke(imageUri)

            val product = Product(UUID.randomUUID().toString(), description, price, imagUrl)
            productRepository.createProduct(product)
        } catch (e: Exception) {
            throw e
        }
    }
}