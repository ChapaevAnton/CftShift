package com.w4eret1ckrtb1tch.homework.presentation.item

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentItemBinding
import com.w4eret1ckrtb1tch.homework.domain.model.Currency
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal

@AndroidEntryPoint
class ItemFragment : Fragment(R.layout.fragment_item) {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ItemViewModel>()
    private val args: ItemFragmentArgs by navArgs()
    private lateinit var currency: Currency

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currency = args.currency
        viewModel.setCurrency(currency)
        viewModel.getCurrency().observe(viewLifecycleOwner) { currency ->
            with(binding) {
                name.text = currency.name
                nominal.text = getString(R.string.nominal, currency.nominal)
                value.text = getString(R.string.value, currency.value)
                charCode.text = currency.charCode
            }
        }
        viewModel.getDeviationRate().observe(viewLifecycleOwner) { deviationRate ->
            binding.rate.text = getString(R.string.rate, deviationRate)
            binding.rate.setTextColor(
                if (deviationRate >= BigDecimal(0))
                    Color.parseColor("#ff669900")
                else Color.parseColor("#ffcc0000")
            )
            binding.rateArrow.setImageResource(if (deviationRate >= BigDecimal(0)) R.drawable.ic_up else R.drawable.ic_down)
        }
        viewModel.getAmountCurrency().observe(viewLifecycleOwner) { amountCurrency ->
            binding.amountCurrency.text = getString(R.string.amount_currency, amountCurrency)
        }

        binding.cash.addTextChangedListener(viewModel.getTextWatcher())

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}