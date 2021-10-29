package com.w4eret1ckrtb1tch.homework.presentation.fragments.list

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentListBinding
import com.w4eret1ckrtb1tch.homework.domain.model.Contact
import com.w4eret1ckrtb1tch.homework.presentation.adapters.ContactAdapter
import com.w4eret1ckrtb1tch.homework.presentation.adapters.RecyclerDecoration


class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val decorator by lazy { RecyclerDecoration(sidePagingDp = 8, bottomPagingDp = 8) }
    private lateinit var contactAdapter: ContactAdapter

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
        contactAdapter = ContactAdapter(onClick = { })
        // TODO: 29.10.2021 set contacts to adapter
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
        checkPermission()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_REQUEST &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("TAG", "onRequestPermissionsResult: ok")
            getContacts()
        } else {
            showDescription("Permission denied")
        }
    }

    private fun showDescription(description: String) {
        Snackbar.make(binding.root, description, Snackbar.LENGTH_SHORT).setMaxInlineActionWidth(
            resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width)
        ).show()
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                CONTACTS_REQUEST
            )
        } else {
            Log.d("TAG", "checkPermission: ok")
            getContacts()
        }
    }

    private fun getContacts() {
        val contacts = mutableListOf<Contact>()
        val telNumber = mutableListOf<String>()
        var contact: Contact
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
        val sort = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        val cursor = requireActivity().contentResolver.query(uri, null, null, null, sort)
        cursor?.let {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    contact = Contact(name)
                    val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    val selection = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?"
                    val phoneCursor =
                        requireActivity().contentResolver.query(
                            uriPhone,
                            null,
                            selection,
                            arrayOf(id),
                            null
                        )
                    phoneCursor?.let {
                        if (phoneCursor.count > 0) {
                            Log.d("TAG", "getContacts: ${phoneCursor.count}")
                            while (phoneCursor.moveToNext()) {
                                val number =
                                    phoneCursor.getString(
                                        phoneCursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER
                                        )
                                    )
                                Log.d("TAG", "getContacts: $name $number ${phoneCursor.position}")
                                when (phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE))) {
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE ->
                                        telNumber.add(number)
                                }
                            }
                        }
                    }
                    phoneCursor?.close()
                    contact.number.addAll(telNumber.filterIndexed { index, _ -> index == 1 })
                    contacts.add(contact)
                }
            }
        }
        cursor?.close()
        contactAdapter.contacts = contacts
    }

    companion object {
        fun newInstance(bundle: Bundle?): ListFragment {
            return ListFragment().apply {
                arguments = bundle
            }
        }

        const val CONTACTS_REQUEST = 100
    }
}