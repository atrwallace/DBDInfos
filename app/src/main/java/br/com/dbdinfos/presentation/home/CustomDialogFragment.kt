package br.com.dbdinfos.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.dbdinfos.data.MainDTO
import br.com.dbdinfos.databinding.FragmentCustomDialogBinding

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