package com.w4eret1ckrtb1tch.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4eret1ckrtb1tch.homework.databinding.ItemContactBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

class ContactAdapter(
    private val onClickDelete: (contact: ContactEntity, position: Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var contacts: MutableList<ContactEntity> = mutableListOf()
        set(value) {
            field = value
            notifyItemRangeInserted(0, field.lastIndex)
        }

    fun removeItem(position: Int) {
        contacts.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding, onClickDelete)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(
        private val binding: ItemContactBinding,
        private val onClickDelete: (contact: ContactEntity, position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contactEntity: ContactEntity) {
            with(binding) {
                name.text = contactEntity.name
                number.text = contactEntity.number
                delete.setOnClickListener {
                    onClickDelete.invoke(contactEntity, adapterPosition)
                }
            }
        }
    }


}