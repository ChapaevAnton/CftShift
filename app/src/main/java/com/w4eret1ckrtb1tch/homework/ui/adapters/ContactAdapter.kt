package com.w4eret1ckrtb1tch.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4eret1ckrtb1tch.homework.databinding.ItemContactBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

class ContactAdapter(
    private val onClickDelete: (contact: ContactDto, position: Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var contacts: MutableList<ContactDto> = mutableListOf()
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
        private val onClickDelete: (contact: ContactDto, position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: ContactDto) {
            with(binding) {
                name.text = contact.name
                number.text = contact.number
                delete.setOnClickListener {
                    onClickDelete.invoke(contact, adapterPosition)
                }
            }
        }
    }


}