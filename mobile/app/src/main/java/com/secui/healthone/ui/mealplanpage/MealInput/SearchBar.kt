package com.secui.healthone.ui.mealplanpage.MealInput

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchBar(search: String,searchTerm: String, onSearchTermChanged: (String) -> Unit) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(searchTerm)) }

    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newTextFieldValue ->
                textFieldValue = newTextFieldValue
            },
            label = { Text("Search $search") },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchTermChanged(textFieldValue.text)
            }),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            trailingIcon = {
                IconButton(onClick = {
                    textFieldValue = TextFieldValue("")
                }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear Text")
                }
            }
        )
    }
}
