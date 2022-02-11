package com.example.dbdinfos.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.dbdinfos.R
import com.example.dbdinfos.data.MainDTO
import com.example.dbdinfos.databinding.FragmentCustomDialogBinding
import org.w3c.dom.Text

class CustomDialogFragment(var mainDTO: MainDTO) : DialogFragment() {
    private lateinit var binding: FragmentCustomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleperk.text = mainDTO.perk_name
        binding.descriptionperk.text = mainDTO.description
    }

}