package dk.easj.anbo.bookstoremvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.obligatoriskopgave.Models.ItemViewModel
import com.example.obligatoriskopgave.SalesItemsOnListArgs
import com.example.obligatoriskopgave.databinding.FragmentSalesItemsOnListBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSalesItemsOnListBinding? = null
    private val binding get() = _binding!!
    private val itemViewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSalesItemsOnListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()
        val salesItemsOnListArgs: SalesItemsOnListArgs = SalesItemsOnListArgs.fromBundle(bundle)
        val position = salesItemsOnListArgs.position




    }
}