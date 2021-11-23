package com.w4eret1ckrtb1tch.homework.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentImageLoadBinding
import com.w4eret1ckrtb1tch.homework.presentation.ImageLoadViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageLoadFragment : Fragment(R.layout.fragment_image_load) {

    private val viewModel by viewModels<ImageLoadViewModel>()
    private var binding: FragmentImageLoadBinding? = null
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageLoadBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            image.setOnClickListener {
                openImageChooser()
            }

            upload.setOnClickListener {
                viewModel.uploadImage(selectedImageUri)
            }

        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    binding?.image?.setImageURI(selectedImageUri)
                }
            }
        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

}