package com.w4eret1ckrtb1tch.homework.presentation.fragments.list

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
import com.w4eret1ckrtb1tch.homework.domain.model.ContactEntity
import com.w4eret1ckrtb1tch.homework.presentation.adapters.ContactAdapter
import com.w4eret1ckrtb1tch.homework.presentation.adapters.RecyclerDecoration


class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val decorator by lazy { RecyclerDecoration(sidePagingDp = 8, bottomPagingDp = 8) }
    private val viewModel: ListViewModel by viewModels()
    private lateinit var contactAdapter: ContactAdapter
    private var mPermissionResult: ActivityResultLauncher<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPermissionResult = registerForActivityResult(
            RequestPermission()
        ) { granted: Boolean ->
            if (granted) {
                Log.d("TEST", "Permission granted...!")
                viewModel.loadContacts()
            } else {
                Log.d("TEST", "Permission denied...!")
                showDescription("Permission denied")
            }
        }
    }

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
        contactAdapter = ContactAdapter(onClickDelete = { contact, position ->
            deleteContact(contact, position)
        })
        binding.recyclerView.addItemDecoration(decorator)
        binding.recyclerView.adapter = contactAdapter
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    Snackbar.make(view, getString(R.string.add), Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        viewModel.checkPermission()
        viewModel.getContacts()
            .observe(viewLifecycleOwner) { contacts ->
                contactAdapter.contacts = contacts.toMutableList()
            }
        viewModel.getPermission().observe(viewLifecycleOwner) { showPermission() }
    }

    override fun onDestroyView() {
        mPermissionResult = null
        _binding = null
        super.onDestroyView()
    }

    private fun deleteContact(contact: ContactEntity, position: Int) {
        viewModel.deleteContact(contact)
        contactAdapter.removeItem(position)
    }

    private fun showPermission() {
        mPermissionResult?.launch(Manifest.permission.READ_CONTACTS, null)
    }

    private fun showDescription(description: String) {
        Snackbar.make(binding.root, description, Snackbar.LENGTH_SHORT).setMaxInlineActionWidth(
            resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width)
        ).show()
    }

    companion object {
        fun newInstance(bundle: Bundle?): ListFragment {
            return ListFragment().apply {
                arguments = bundle
            }
        }
    }

}