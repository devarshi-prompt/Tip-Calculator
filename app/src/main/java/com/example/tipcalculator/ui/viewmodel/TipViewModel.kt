package com.example.tipcalculator.ui.viewmodel

import android.text.Editable
import android.view.View
import android.widget.SeekBar
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class TipViewModel : ViewModel() {
    val totalAmount = ObservableField("0.00")
    val amountToSplit = ObservableField("")
    val personCount = ObservableField("1")
    val tipAmount = ObservableField("0.00")
    val tipPercentage = ObservableField("0")
    val personHeader = ObservableField("person")
    val enableTipParams = ObservableBoolean(false)
    val maxProgress = ObservableInt(0)
    val checkTipPercentage = ObservableBoolean(true)

    fun onClickPersonIncrement(view: View){
        personCount.set(personCount.get().toString().toInt().inc().toString())
        if (personCount.get().toString().toInt() == 1){
            personHeader.set("person")
        } else{
            personHeader.set("persons")
            val tipAmt = ((amountToSplit.get().toString().toDouble()*tipPercentage.get().toString().toInt())/100)/personCount.get().toString().toInt()
            tipAmount.set(String.format("%.2f",tipAmt))
        }
        validateTipParams()
    }

    fun onClickPersonDecrement(view: View){
        if (personCount.get().toString().toInt() > 1){
            personCount.set(personCount.get().toString().toInt().dec().toString())
        }
        if (personCount.get().toString().toInt() == 1){
            personHeader.set("person")
        } else{
            personHeader.set("persons")
            val tipAmt = ((amountToSplit.get().toString().toDouble()*tipPercentage.get().toString().toInt())/100)/personCount.get().toString().toInt()
            tipAmount.set(String.format("%.2f",tipAmt))
        }
        validateTipParams()
    }

    fun afterEditTextChanged(editable: Editable){
        if (editable.toString().isEmpty()){
            resetAllValues()
        } else{
            maxProgress.set(100)
            enableTipParams.set(true)
            tipAmount.set(String.format("%.2f",(amountToSplit.get().toString().toDouble()*tipPercentage.get().toString().toInt())/100))
            validateTipParams()
        }
    }

    fun onSeekBarChange(seekBar: SeekBar, progress: Int, fromUser: Boolean){
        if (amountToSplit.get().toString().isNotEmpty()){
            tipPercentage.set(progress.toString())
            tipAmount.set(String.format("%.2f",(amountToSplit.get().toString().toDouble()*tipPercentage.get().toString().toInt())/100))
            validateTipParams()
        }
    }

    private fun calculateTotalAmount(isTipZero: Boolean){
        val totalAmt: Double
        if (isTipZero){
            checkTipPercentage.set(true)
            totalAmt = amountToSplit.get().toString().toDouble()/personCount.get().toString().toInt()
            totalAmount.set(String.format("%.2f",totalAmt))
        } else{
            checkTipPercentage.set(false)
            totalAmt = (amountToSplit.get().toString().toDouble()+tipAmount.get().toString().toDouble())/personCount.get().toString().toInt()
            totalAmount.set(String.format("%.2f",totalAmt))
        }
    }

    private fun validateTipParams(){
        if (amountToSplit.get() != "" && tipPercentage.get().toString().toInt() != 0){
            calculateTotalAmount(false)
        }
        else if (amountToSplit.get() != "" && tipPercentage.get().toString().toInt() == 0){
            calculateTotalAmount(true)
        }
    }

    private fun resetAllValues() {
        tipAmount.set("0.00")
        tipPercentage.set("0")
        totalAmount.set("0.00")
        personCount.set("1")
        personHeader.set("person")
        enableTipParams.set(false)
        maxProgress.set(0)
    }
}