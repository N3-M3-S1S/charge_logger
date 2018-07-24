package com.nemesis.chargelogger.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nemesis.chargelogger.R
import com.nemesis.chargelogger.databinding.ChargeBinding
import com.nemesis.chargelogger.model.Charge
import kotlin.properties.Delegates


class ChargeLogAdapter : RecyclerView.Adapter<ChargeViewHolder>() {

    var chargeLog: List<Charge> by Delegates.observable(emptyList()) { _, oldValue, newValue -> DiffUtil.calculateDiff(DiffCallback(oldValue, newValue)).dispatchUpdatesTo(this) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChargeViewHolder = ChargeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.charge, parent, false))

    override fun getItemCount(): Int = chargeLog.size

    override fun onBindViewHolder(holder: ChargeViewHolder, position: Int) = holder.bind(chargeLog[position])

}


class ChargeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var chargeBinding: ChargeBinding

    fun bind(charge: Charge) {
        chargeBinding = DataBindingUtil.bind(view)!!
        chargeBinding.charge = charge
    }

    fun getCharge(): Charge = chargeBinding.charge!!
}


private class DiffCallback(private val old: List<Charge>, private val new: List<Charge>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old[oldItemPosition] == new[newItemPosition]

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = areItemsTheSame(oldItemPosition, newItemPosition)
}