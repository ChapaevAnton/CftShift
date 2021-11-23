package com.w4eret1ckrtb1tch.homework.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentImageLoadBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.presentation.ImageLoadViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageLoadFragment : Fragment(R.layout.fragment_image_load) {

    private val viewModel by viewModels<ImageLoadViewModel>()
    private var binding: FragmentImageLoadBinding? = null
    private var selectedImageUri: Uri? = null
    private lateinit var someActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        someActivityResultLauncher = registerForActivityResult(
            StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    selectedImageUri = it.data
                    binding?.image?.setImageURI(selectedImageUri)
                }
            }
        }
    }

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
        viewModel.getUploadResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> resolveSuccess(it.value.url)
                is Result.Failure -> resolveFailure(it.exception)
            }
        }
        viewModel.getUploadProgress.observe(viewLifecycleOwner) {
            binding?.apply { progressBar.progress = it }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun resolveSuccess(url: String?) {
        url?.let { binding?.root?.showMessage(it) }
    }

    private fun resolveFailure(exception: Throwable?) {
        exception?.let { binding?.root?.showMessage(it.message.toString()) }
    }

    private fun View.showMessage(description: String) {
        val snackBar = Snackbar
            .make(this, description, Snackbar.LENGTH_INDEFINITE)
        snackBar
            .setMaxInlineActionWidth(resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width))
            .setAction(R.string.ok) { snackBar.dismiss() }
            .show()
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            someActivityResultLauncher.launch(it)
        }
    }
}