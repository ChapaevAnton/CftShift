package com.w4eret1ckrtb1tch.homework.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4eret1ckrtb1tch.homework.databinding.ItemContactBinding
import com.w4eret1ckrtb1tch.homework.domain.model.ContactEntity

class ContactAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var contactEntities: List<ContactEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactEntities[position])
    }

    override fun getItemCount(): Int = contactEntities.size

    class ContactViewHolder(
        private val binding: ItemContactBinding,
        private val onClick: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contactEntity: ContactEntity) {
            with(binding) {
                name.text = contactEntity.name
                number.text = contactEntity.number.toString()
            }
        }
    }


}