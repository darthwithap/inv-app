package me.darthwithap.invapp.ui.addstock.godownstock

/**
 * Data validation state of the add stock form.
 */
data class AddStockFormState(
    val nameError: Int? = null,
    val quantityError: Int? = null,
    val isDataValid: Boolean = false
)