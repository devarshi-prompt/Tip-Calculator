package com.example.tipcalculator.ui.viewmodel

import android.text.Editable
import android.view.View
import android.widget.SeekBar
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class TipViewModel : ViewModel() {
    val totalAmount = ObservableField("0")
    val amountToSplit = ObservableField("")
    val personCount = ObservableField("0")
    val tipAmount = ObservableField("0")
    val tipPercentage = ObservableField("0")
    val personHeader = ObservableField("person")
    val enableTipParams = ObservableBoolean(false)
    val maxProgress = ObservableInt(0)

    fun onClickPersonIncrement(view: View){
        personCount.set(personCount.get().toString().toInt().inc().toString())
        if (personCount.get().toString().toInt() == 1 || personCount.get().toString().toInt() == 0){
            personHeader.set("person")
        } else{
            personHeader.set("persons")
        }
        validateTipParams()
    }

    fun onClickPersonDecrement(view: View){
        if (personCount.get().toString().toInt() > 0){
            personCount.set(personCount.get().toString().toInt().dec().toString())
        }
        if (personCount.get().toString().toInt() == 1 || personCount.get().toString().toInt() == 0){
            personHeader.set("person")
        } else{
            personHeader.set("persons")
        }
        validateTipParams()
    }

    fun calculateTip(editable: Editable){
        totalAmount.set(amountToSplit.get().toString())
        if (editable.toString().isEmpty()){
            tipAmount.set("0")
            tipPercentage.set("0")
            totalAmount.set("0")
            personCount.set("0")
            personHeader.set("person")
            enableTipParams.set(false)
            maxProgress.set(0)
        } else{
            maxProgress.set(100)
            enableTipParams.set(true)
        }
    }

    fun showTipPercentAndAmount(seekBar: SeekBar,progress: Int,fromUser: Boolean){
        if (amountToSplit.get().toString().isNotEmpty()){
            tipPercentage.set(progress.toString())
            tipAmount.set(progress.toString())
            validateTipParams()
        }
    }

    private fun calculateTotalAmount(isTipZero: Boolean,isPersonCountZero: Boolean){
        val totalAmt: Double
        if (isTipZero){
            if (isPersonCountZero){
                totalAmt = amountToSplit.get().toString().toDouble()
                totalAmount.set(String.format("%.2f",totalAmt))
            } else{
                totalAmt = amountToSplit.get().toString().toDouble()/personCount.get().toString().toInt()
                totalAmount.set(String.format("%.2f",totalAmt))
            }
        } else{
            if (isPersonCountZero){
                totalAmt = amountToSplit.get().toString().toDouble()
                totalAmount.set(String.format("%.2f",totalAmt))
            } else{
                totalAmt = (amountToSplit.get().toString().toDouble()+tipAmount.get().toString().toInt())/personCount.get().toString().toInt()
                totalAmount.set(String.format("%.2f",totalAmt))
            }
        }
    }

    private fun validateTipParams(){
        if (amountToSplit.get() != "" && tipAmount.get().toString().toInt() != 0 && personCount.get().toString().toInt() != 0){
            calculateTotalAmount(false, isPersonCountZero = false)
        }
        else if (amountToSplit.get() != "" && tipAmount.get().toString().toInt() == 0 && personCount.get().toString().toInt() != 0){
            calculateTotalAmount(true,isPersonCountZero = false)
        }
        else if (amountToSplit.get() != "" && tipAmount.get().toString().toInt() != 0 && personCount.get().toString().toInt() == 0){
            calculateTotalAmount(false, isPersonCountZero = true)
        }
        else if (amountToSplit.get() != "" && tipAmount.get().toString().toInt() == 0 && personCount.get().toString().toInt() == 0){
            calculateTotalAmount(true, isPersonCountZero = true)
        }
    }
}