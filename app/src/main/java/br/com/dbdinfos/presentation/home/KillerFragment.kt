package br.com.dbdinfos.presentation.home


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dbdinfos.R
import br.com.dbdinfos.databinding.FragmentKillerBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class KillerFragment : Fragment(R.layout.fragment_killer) {
    private lateinit var binding: FragmentKillerBinding
    private val vm: HomeViewModel by sharedViewModel()
    lateinit var adapter: MainDTOAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKillerBinding.inflate(layoutInflater)
        val view: View = binding.root
        setHasOptionsMenu(true)
        adapter = MainDTOAdapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        val perkList = binding.recyclerviewKiller
        perkList.layoutManager = layoutManager
        perkList.setHasFixedSize(true)
        perkList.adapter = adapter

        return view
    }

    /**
    Demonstrando uso do Shimmer. Após testes veremos a necessidade de retirada do Handler
     **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            setupRecyclerViewToAdapter()
        }, 3000)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerViewToAdapter() {
        adapter.setOnClickListenerDTO {
            val alert = CustomDialogFragment(it)
            val manager = (context as AppCompatActivity).supportFragmentManager

            alert.show(manager, "customDialog")
        }
        vm.allPerks.observe(viewLifecycleOwner, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adapter.setDBDLIST(it.filter { it.role == "Killer" })
        })
    }
}