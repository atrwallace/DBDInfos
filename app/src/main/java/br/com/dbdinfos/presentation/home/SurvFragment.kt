package br.com.dbdinfos.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dbdinfos.R
import br.com.dbdinfos.databinding.FragmentSurvBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SurvFragment : Fragment(R.layout.fragment_surv) {
    private lateinit var binding: FragmentSurvBinding
    private val vm: HomeViewModel by sharedViewModel()
    lateinit var adapter: MainDTOAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurvBinding.inflate(layoutInflater)
        val view: View = binding.root
        setHasOptionsMenu(true)
        adapter = MainDTOAdapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        val perkList = binding.recyclerviewSurv
        perkList.layoutManager = layoutManager
        perkList.setHasFixedSize(true)
        perkList.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewToAdapter()
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerViewToAdapter(){
        adapter.setOnClickListenerDTO {
            val alert = CustomDialogFragment(it)
            val manager = (context as AppCompatActivity).supportFragmentManager

            alert.show(manager, "customDialog")
        }
        vm.allPerks.observe(viewLifecycleOwner, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adapter.setDBDLIST(it.filter { it.role == "Survivor" })
        })
    }

}