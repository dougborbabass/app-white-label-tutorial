package br.com.douglas.whitelabeltutorial.domain.usecase

import android.net.Uri

interface UploadProductImageUseCase {
    suspend operator fun invoke(imageUri: Uri): String
}