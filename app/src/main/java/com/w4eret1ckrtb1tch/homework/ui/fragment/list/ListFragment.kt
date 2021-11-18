package com.w4eret1ckrtb1tch.homework.ui.fragment.list

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.BuildConfig
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentListBinding
import com.w4eret1ckrtb1tch.homework.presentation.utils.ViewModelFactoryImpl
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.ListViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.homework.ui.adapter.ItemAdapter
import com.w4eret1ckrtb1tch.homework.ui.adapter.RecyclerDecoration
import dagger.Lazy
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ListFragment @Inject constructor() : DaggerFragment(R.layout.fragment_list) {

    private var binding: FragmentListBinding? = null
    private lateinit var adapter: ItemAdapter
    private val decorator by lazy { RecyclerDecoration(sidePagingDp = 8, bottomPagingDp = 8) }

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelFactoryImpl>
    private val viewModel by viewModels<ListViewModel>(factoryProducer = { viewModelFactory.get() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apply()

        viewModel.getItems.observe(viewLifecycleOwner) { items ->
            adapter.listItem = items
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun apply() {
        binding?.apply {
            adapter = ItemAdapter(
                onClickPortfolio = { name ->
                    showDescription(name)
                },
                onClickBannerAccept = {
                    Snackbar.make(root, getString(R.string.accept), Snackbar.LENGTH_SHORT).show()
                },
                onClickBannerCancel = {
                    Snackbar.make(root, getString(R.string.cancel), Snackbar.LENGTH_SHORT).show()
                }
            )
            recyclerView.addItemDecoration(decorator)
            recyclerView.adapter = adapter
            if (BuildConfig.DEBUG) toolbar.title =
                "${toolbar.title} ${getString(R.string.app_name)}"
            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.add -> {
                        (requireActivity() as MainActivity).let {
                            it.fragmentRouter.get().openAddedFragment(it, null)
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun showDescription(description: String) {
        Snackbar.make(binding?.root!!, description, Snackbar.LENGTH_SHORT).setMaxInlineActionWidth(
            resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width)
        ).show()
    }
}