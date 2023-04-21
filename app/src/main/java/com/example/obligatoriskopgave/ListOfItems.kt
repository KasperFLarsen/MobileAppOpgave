import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
//import com.example.obligatoriskopgave.ListOfItemsDirections
import com.example.obligatoriskopgave.Models.Adapter
import com.example.obligatoriskopgave.Models.Item
import com.example.obligatoriskopgave.Models.ItemViewModel
import com.example.obligatoriskopgave.R
import com.example.obligatoriskopgave.databinding.FragmentListOfItemsBinding
import java.util.*

class ListOfItems : Fragment() {

    private var _binding: FragmentListOfItemsBinding? = null
    private val binding get() = _binding!!
    private val listedItemsViewModel: ItemViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonLogoff.setOnClickListener{
            findNavController().navigate(R.id.action_listOfItems_to_logIn)
        }

        binding.buttonAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listOfItems_to_addItem)
        }





        listedItemsViewModel.itemslivedata.observe(viewLifecycleOwner) { items ->
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

        listedItemsViewModel.errormessagelivedata.observe(viewLifecycleOwner) { errorMessage ->
            binding.textviewMessage.text = errorMessage
        }

        listedItemsViewModel.reload()

        binding.swiperefresh.setOnRefreshListener {
            listedItemsViewModel.reload()
            binding.swiperefresh.isRefreshing = false
        }

        binding.buttonSort.setOnClickListener {
            Log.d("madpakke", binding.spinnerSorting.selectedItemPosition.toString())
            when (binding.spinnerSorting.selectedItemPosition) {
                0 -> listedItemsViewModel.sortByDescription()
                1 -> listedItemsViewModel.sortByDescriptionDescending()
                2 -> listedItemsViewModel.sortByPrice()
                3 -> listedItemsViewModel.sortByPriceDescending()

            }
        }

        binding.buttonFilter.setOnClickListener {
            fun String.capitalized(): String {
                return this.replaceFirstChar {
                    if (it.isLowerCase())
                        it.titlecase(Locale.getDefault())
                    else it.toString()
                }
            }

            val description = binding.edittextFilterTitle.text.toString().trim().capitalized()
            listedItemsViewModel.filterByDescription(description)
        }

    }
}