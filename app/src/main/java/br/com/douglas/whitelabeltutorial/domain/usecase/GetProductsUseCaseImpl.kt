package br.com.douglas.whitelabeltutorial.domain.usecase

import br.com.douglas.whitelabeltutorial.data.ProductRepository
import br.com.douglas.whitelabeltutorial.domain.model.Product

class GetProductsUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductsUseCase {

    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}