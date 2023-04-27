package com.secui.healthone.ui.mealplanpage.MealInput

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(
    searchTerm: String,
    onSearchTermChanged: (String) -> Unit
) {
    TextField(
        value = searchTerm,
        onValueChange = onSearchTermChanged,
        label = { Text("검색") },
        modifier = Modifier.fillMaxWidth()
    )
}
