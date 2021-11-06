package com.w4eret1ckrtb1tch.homework.ui.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentListBinding
import com.w4eret1ckrtb1tch.homework.di.Injection
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list.ListViewModel
import com.w4eret1ckrtb1tch.homework.ui.adapters.ContactAdapter
import com.w4eret1ckrtb1tch.homework.ui.adapters.RecyclerDecoration


class ListFragment : Fragment(R.layout.fragment_list) {

    private var binding: FragmentListBinding? = null
    private val decorator by lazy { RecyclerDecoration(sidePagingDp = 8, bottomPagingDp = 8) }
    private val viewModel: ListViewModel by viewModels(factoryProducer = {
        Injection.provideListViewModel(
            this.requireContext(), this
        )
    })
    private val contactAdapter by lazy {
        ContactAdapter(onClickDelete = { contact, position ->
            deleteContact(contact, position)
        })
    }
    private var mPermissionResult: ActivityResultLauncher<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPermissionResult = registerForActivityResult(
            RequestPermission()
        ) { granted: Boolean ->
            if (granted) {
                Log.d("TAG", "Permission granted...!")
                viewModel.updateContacts()
            } else {
                Log.d("TAG", "Permission denied...!")
                showDescription("Permission denied")
            }
        }
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
        viewModel.checkPermission()
        viewModel.contacts
            .observe(viewLifecycleOwner) { contacts ->
                contactAdapter.contacts = contacts.toMutableList()
            }
        viewModel.showPermission.observe(viewLifecycleOwner) { showPermission() }
    }

    override fun onDestroyView() {
        mPermissionResult = null
        binding = null
        super.onDestroyView()
    }

    private fun apply() {
        binding?.apply {
            recyclerView.addItemDecoration(decorator)
            recyclerView.adapter = contactAdapter
            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.add -> {
                        Snackbar.make(root, getString(R.string.add), Snackbar.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun deleteContact(contact: ContactDto, position: Int) {
        viewModel.removeContact(contact)
        contactAdapter.removeItem(position)
    }

    private fun showPermission() {
        mPermissionResult?.launch(Manifest.permission.READ_CONTACTS, null)
    }

    private fun showDescription(description: String) {
        binding?.let {
            Snackbar.make(it.root, description, Snackbar.LENGTH_SHORT).setMaxInlineActionWidth(
                resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width)
            ).show()
        }
    }

    companion object {
        fun newInstance(bundle: Bundle?): ListFragment {
            return ListFragment().apply {
                arguments = bundle
            }
        }
    }

}