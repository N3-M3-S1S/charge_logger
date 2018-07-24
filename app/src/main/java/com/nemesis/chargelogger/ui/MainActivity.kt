package com.nemesis.chargelogger.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.nemesis.chargelogger.R
import com.nemesis.chargelogger.databinding.MainActivityBinding
import com.nemesis.chargelogger.ui.adapter.ChargeLogAdapter
import com.nemesis.chargelogger.ui.adapter.ChargeViewHolder
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), AnkoLogger {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityBinding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get()
        lifecycle.addObserver(mainViewModel)
        mainActivityBinding.viewmodel = mainViewModel

        val chargeLogAdapter = ChargeLogAdapter()
        chargeLogRv.adapter = chargeLogAdapter
        ItemTouchHelper(getSwipeCallback()).attachToRecyclerView(chargeLogRv)

        mainViewModel.chargeHistory.observe(this, Observer { chargeLogAdapter.chargeLog = it; mainActivityBinding.isChargeHistoryNotEmpty = it.isNotEmpty() })

    }

    private fun getSwipeCallback(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int = makeMovementFlags(0, LEFT or RIGHT)
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = with(viewHolder as ChargeViewHolder) { mainViewModel.onChargeSwiped(viewHolder.getCharge()) }
        }
    }


}


