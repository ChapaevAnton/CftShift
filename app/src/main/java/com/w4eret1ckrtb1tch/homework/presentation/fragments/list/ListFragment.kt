package com.w4eret1ckrtb1tch.homework.presentation.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.data.Data
import com.w4eret1ckrtb1tch.homework.databinding.FragmentListBinding
import com.w4eret1ckrtb1tch.homework.presentation.adapters.ItemAdapter
import com.w4eret1ckrtb1tch.homework.presentation.adapters.RecyclerDecoration

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val decorator by lazy { RecyclerDecoration(sidePagingDp = 8, bottomPagingDp = 8) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter(
            onClickPortfolio = { name ->
                Snackbar.make(view, name, Snackbar.LENGTH_SHORT).show()
            },
            onClickBannerAccept = {
                Snackbar.make(view, getString(R.string.accept), Snackbar.LENGTH_SHORT).show()
            },
            onClickBannerCancel = {
                Snackbar.make(view, getString(R.string.cancel), Snackbar.LENGTH_SHORT).show()
            }
        )
        itemAdapter.listItem = (0..100).map { Data.data }.flatten()
        binding.recyclerView.addItemDecoration(decorator)
        binding.recyclerView.adapter = itemAdapter
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    Snackbar.make(view, getString(R.string.add), Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(bundle: Bundle?): ListFragment {
            return ListFragment().apply {
                arguments = bundle
            }
        }
    }
}