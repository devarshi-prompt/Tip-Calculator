package com.example.tipcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tipcalculator.databinding.FragmentCalculateTipBinding
import com.example.tipcalculator.ui.viewmodel.TipViewModel
import com.example.tipcalculator.utils.Utils
import com.example.tipcalculator.utils.Utils.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CalculateTipFragment : Fragment() {

    private lateinit var binding: FragmentCalculateTipBinding
    private val viewModel: TipViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentCalculateTipBinding.inflate(layoutInflater,container,false)
        binding.viewmodel = viewModel
        return binding.root
    }
}