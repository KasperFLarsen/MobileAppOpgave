package com.example.obligatoriskopgave

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.obligatoriskopgave.Models.ItemViewModel
import com.example.obligatoriskopgave.databinding.FragmentListOfItemsBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.obligatoriskopgave.Models.Adapter
import com.google.firebase.auth.FirebaseAuth

class ListOfItems: Fragment(){
    private var _binding: FragmentListOfItemsBinding?=null
    private val binding get() = _binding!!
    private val itemViewModel: ItemViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel.itemslivedata.observe(viewLifecycleOwner) { items ->
            binding.progressbar.visibility = View.GONE
            binding.recyclerView.visibility = if (items == null) View.GONE else View.VISIBLE
            if (items != null) {
                val adapter = Adapter(items) { position ->
                    val action =
                        ListOfItemsDirections.actionListOfItemsToSalesItemsOnList(position)
                    findNavController().navigate(action)
                }
                var columns = 1
                val currentOrientation = this.resources.configuration.orientation
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    columns = 2
                } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    columns = 1
                }

                binding.recyclerView.layoutManager = GridLayoutManager(this.context, columns)

                binding.recyclerView.adapter = adapter
            }
        }
        itemViewModel.errormessagelivedata.observe(viewLifecycleOwner) { errorMessage ->
            binding.textviewMessage.text = errorMessage
        }

        binding.buttonLogoff.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_listOfItems_to_logIn)
        }

        itemViewModel.reload()
        binding.swiperefresh.setOnRefreshListener {
            itemViewModel.reload()
            binding.swiperefresh.isRefreshing = false
        }
    }
}


