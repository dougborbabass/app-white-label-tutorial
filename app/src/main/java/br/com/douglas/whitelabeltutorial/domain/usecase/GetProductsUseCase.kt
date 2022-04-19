package br.com.douglas.whitelabeltutorial.domain.usecase

import br.com.douglas.whitelabeltutorial.domain.model.Product

interface GetProductsUseCase {
    suspend operator fun invoke(): List<Product>
}