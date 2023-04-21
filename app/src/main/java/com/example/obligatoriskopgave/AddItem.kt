import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.obligatoriskopgave.Models.Item
import com.example.obligatoriskopgave.Models.ItemViewModel
import com.example.obligatoriskopgave.R
import com.example.obligatoriskopgave.databinding.FragmentAddItem2Binding
import com.example.obligatoriskopgave.databinding.FragmentSalesItemsOnListBinding


class AddItem : Fragment() {
    private var _binding: FragmentAddItem2Binding? = null
    private val binding get() = _binding!!
    private val listedItemsViewModel: ItemViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItem2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        binding.addItemButton.setOnClickListener{
            val description = binding.editTextDescription.text.toString()
            var price = 0
            val sellerEmail = binding.editTextEmail.text.toString()
            val sellerPhone = binding.editTextPhone.text.toString()
            val time = System.currentTimeMillis()/1000
            val pictureUrl = binding.editTextPictureUrl.text.toString()

            val priceText = binding.editTextPrice.text.toString()
            if(priceText.isNotEmpty()) price = priceText.toInt() else 0

            val newListing = Item(description,price,sellerEmail,sellerPhone,time,pictureUrl)

            listedItemsViewModel.add(newListing)

            findNavController().navigate(R.id.action_addItem_to_listOfItems)



        }
        binding.cancelButton.setOnClickListener{
            findNavController().navigate(R.id.action_addItem_to_listOfItems)
        }





    }





}

