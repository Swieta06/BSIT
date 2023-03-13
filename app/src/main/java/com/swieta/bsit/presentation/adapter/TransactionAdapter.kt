package com.swieta.bsit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swieta.bsit.R
import com.swieta.bsit.databinding.ItemTransactionBinding
import com.swieta.bsit.model.TransactionResponse

class TransactionAdapter(
    private val data: List<TransactionResponse>
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = TransactionViewHolder(
        ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bindingView(data[position])
    }

    inner class TransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bindingView(item: TransactionResponse){
                binding.tvUser.text=item.name
                binding.tvMethod.text=item.metodeTrf
                binding.tvNominal.text=item.nominalSaldo.toString()
                if(item.flagDebitCredit==1){

                    binding.tvNominal.setTextColor(
                        binding.root.context.resources.getColor(R.color.red))

                }else{
                    binding.tvNominal.setTextColor(
                        binding.root.context.resources.getColor(R.color.green))
                }

                Glide
                    .with(binding.root.context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(binding.ivUser)

            }

    }
}

