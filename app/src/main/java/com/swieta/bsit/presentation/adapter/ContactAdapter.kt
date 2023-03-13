package com.swieta.bsit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swieta.bsit.R
import com.swieta.bsit.databinding.ItemContactBinding
import com.swieta.bsit.databinding.ItemTransactionBinding
import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.TransactionResponse

class ContactAdapter(

) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ContactViewHolder(
        ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindingView(data[position])
    }


    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindingView(item: ContactResponse) {
            binding.tvUser.text = item.name
            binding.tvMethod.text = item.noTelp
            binding.ivCall.setOnClickListener { onClickPhone.invoke(item) }
            // binding.tvNominal.text=item.nominalSaldo.toString()

//            Glide
//                .with(binding.root.context)
//                .load(item.imageUrl)
//                .centerCrop()
//                .into(binding.icPhone)

        }


    }
    private var data: MutableList<ContactResponse> = mutableListOf()
    private  var onClickPhone : (ContactResponse) -> Unit = {}

    fun setData(newData : MutableList<ContactResponse>){
        data = newData
        notifyDataSetChanged()
    }
    fun onClickCall(clickContact: (ContactResponse)->Unit){
        onClickPhone = clickContact
    }


}