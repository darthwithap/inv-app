package me.darthwithap.invapp.ui.invoice

data class InvoiceFormState(
    val customerError: Int? = null,
    val godownError: Int? = null,
    val productsError: Int? = null,
    val isDataValid: Boolean = false
)