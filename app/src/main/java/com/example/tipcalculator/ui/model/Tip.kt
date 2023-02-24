package com.example.tipcalculator.ui.model

data class Tip(
    var totalAmount: Double,
    var amountToSplit: Double,
    var personCount: Int,
    var tipAmount: Int,
    var tipPercentage: Int
)
