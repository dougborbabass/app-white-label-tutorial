package br.com.douglas.whitelabeltutorial.data

import android.net.Uri
import br.com.douglas.whitelabeltutorial.BuildConfig
import br.com.douglas.whitelabeltutorial.domain.model.Product
import br.com.douglas.whitelabeltutorial.util.COLLECTION_PRODUCTS
import br.com.douglas.whitelabeltutorial.util.COLLECTION_ROOT
import br.com.douglas.whitelabeltutorial.util.STORAGE_IMAGES
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore,
    firebaseStorage: FirebaseStorage
) : ProductDataSource {

    // Estrutura dos arquivos no firebase -> Colecao - Documento - Colecao
    // data/car/products/timestamp/ProductA
    // data/bike/products/timestamp/ProductB

    private val documentReference = firebaseFirestore
        .document("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}")

    private val storageReference = firebaseStorage.reference

    override suspend fun getProducts(): List<Product> {

        return suspendCoroutine { continuation ->
            val productsReference = documentReference.collection(COLLECTION_PRODUCTS)
            productsReference.get().addOnSuccessListener { documents ->
                val products = mutableListOf<Product>()
                for (document in documents) {
                    document.toObject(Product::class.java).run {
                        products.add(this)
                    }
                }
                continuation.resumeWith(Result.success(products))
            }

            productsReference.get().addOnFailureListener { excepetion ->
                continuation.resumeWith(Result.failure(excepetion))
            }
        }
    }

    override suspend fun uploadProductImage(imageUri: Uri): String {
        return suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID()
            val childReference = storageReference.child(
                "$STORAGE_IMAGES/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/$randomKey"
            )

            childReference.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val path = uri.toString()
                        continuation.resumeWith(Result.success(path))
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun createProduct(product: Product): Product {
        return suspendCoroutine { continuation ->
            documentReference
                .collection(COLLECTION_PRODUCTS)
                .document(System.currentTimeMillis().toString())
                .set(product)
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(product))
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }
}