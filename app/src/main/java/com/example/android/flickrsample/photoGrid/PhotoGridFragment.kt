package com.example.android.flickrsample.photoGrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.flickrsample.R
import com.example.android.flickrsample.databinding.FragmentPhotoGridBinding

class PhotoGridFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPhotoGridBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_photo_grid,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val viewModelFactory = PhotoGridViewModelFactory(application)

        val photoGridViewModel =
            ViewModelProviders
                .of(this, viewModelFactory)
                .get(PhotoGridViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = photoGridViewModel

        binding.photoList.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            photoGridViewModel.navigateToSelectedPhoto.observe(this, Observer {
            })
        })

        return binding.root
    }
}