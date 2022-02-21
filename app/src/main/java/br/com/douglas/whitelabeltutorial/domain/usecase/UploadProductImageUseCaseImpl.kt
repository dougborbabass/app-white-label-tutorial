package br.com.douglas.whitelabeltutorial.domain.usecase

import android.net.Uri
import br.com.douglas.whitelabeltutorial.data.ProductRepository

class UploadProductImageUseCaseImpl(
    private val productRepository: ProductRepository
) : UploadProductImageUseCase {

    override suspend fun invoke(imageUri: Uri): String {
        return productRepository.uploadProductImage(imageUri)
    }
}