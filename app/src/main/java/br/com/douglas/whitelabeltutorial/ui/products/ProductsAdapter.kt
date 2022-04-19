package br.com.douglas.whitelabeltutorial.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.douglas.whitelabeltutorial.databinding.ItemProductBinding
import br.com.douglas.whitelabeltutorial.domain.model.Product
import br.com.douglas.whitelabeltutorial.util.toCurrency
import com.bumptech.glide.Glide

class ProductsAdapter : ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductsViewHolder(
        private val itemProductBinding: ItemProductBinding
    ) : RecyclerView.ViewHolder(itemProductBinding.root) {

        fun bind(product: Product) {
            itemProductBinding.run {
                Glide.with(itemView)
                    .load(product.imageUrl)
                    .fitCenter()
                    .into(imageProduct)

                textDescription.text = product.description
                textPrice.text = product.price.toCurrency()
            }
        }

        companion object {
            fun create(parent: ViewGroup): ProductsViewHolder {
                val itemProductBinding = ItemProductBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return ProductsViewHolder(itemProductBinding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}