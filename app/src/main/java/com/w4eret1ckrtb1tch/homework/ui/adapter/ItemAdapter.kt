package com.w4eret1ckrtb1tch.homework.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4eret1ckrtb1tch.homework.databinding.ItemBannerBinding
import com.w4eret1ckrtb1tch.homework.databinding.ItemStudentBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem

class ItemAdapter(
    private val onClickPortfolio: (String) -> Unit,
    private val onClickBannerAccept: () -> Unit,
    private val onClickBannerCancel: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val STUDENT_VIEW_TYPE = 0
        const val BANNER_VIEW_TYPE = 1
    }

    var listItem: List<ListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            STUDENT_VIEW_TYPE -> {
                StudentViewHolder(parent, onClickPortfolio)
            }
            BANNER_VIEW_TYPE -> {
                BannerViewHolder(parent, onClickBannerAccept, onClickBannerCancel)
            }
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StudentViewHolder -> holder.bind(listItem[position])
            is BannerViewHolder -> holder.bind(listItem[position])
        }
    }

    override fun getItemCount(): Int = listItem.size

    override fun getItemViewType(position: Int): Int {
        return when (listItem[position]) {
            is ListItem.StudentItem -> STUDENT_VIEW_TYPE
            is ListItem.BannerItem -> BANNER_VIEW_TYPE
        }
    }

    class StudentViewHolder private constructor(
        private val binding: ItemStudentBinding,
        private val onClickPortfolio: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                parent: ViewGroup,
                onClickPortfolio: (String) -> Unit
            ): StudentViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemStudentBinding.inflate(inflater, parent, false)
                return StudentViewHolder(binding, onClickPortfolio)
            }
        }

        fun bind(student: ListItem) {
            if (student is ListItem.StudentItem) {
                with(binding) {
                    val name = "${student.secondName} ${student.name}"
                    fullName.text = name
                    description.text = student.description
                    hasPortfolio.visibility =
                        if (student.hasPortfolio) View.VISIBLE else View.GONE
                    hasPortfolio.setOnClickListener { onClickPortfolio.invoke("$name\n ${student.description}") }
                }
            }
        }
    }

    class BannerViewHolder private constructor(
        private val binding: ItemBannerBinding,
        private val onClickBannerAccept: () -> Unit,
        private val onClickBannerCancel: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                parent: ViewGroup,
                onClickBannerAccept: () -> Unit,
                onClickBannerCancel: () -> Unit
            ): BannerViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBannerBinding.inflate(inflater, parent, false)
                return BannerViewHolder(binding, onClickBannerAccept, onClickBannerCancel)
            }
        }

        fun bind(banner: ListItem) {
            if (banner is ListItem.BannerItem) {
                with(binding) {
                    title.text = banner.title
                    description.text = banner.description
                    accept.setOnClickListener { onClickBannerAccept.invoke() }
                    cancel.setOnClickListener { onClickBannerCancel.invoke() }
                }
            }
        }
    }

}