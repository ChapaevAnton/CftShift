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
import com.w4eret1ckrtb1tch.homework.presentation.fragments.added.AddedFragment


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
                showDescription(name)
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
                    openAddedFragment(null)
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

    private fun showDescription(description: String) {
        Snackbar.make(binding.root, description, Snackbar.LENGTH_SHORT).setMaxInlineActionWidth(
            resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width)
        ).show()
    }

    private fun openAddedFragment(bundle: Bundle?) {
        val fragment = AddedFragment.newInstance(bundle)
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in,
                R.anim.slide_out
            )
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(fragment::class.simpleName)
            .commit()
    }


    companion object {
        fun newInstance(bundle: Bundle?): ListFragment {
            return ListFragment().apply {
                arguments = bundle
            }
        }

        const val KEY_LIST_FRAGMENT = "com.homework.list_fragment.arguments"
    }
}