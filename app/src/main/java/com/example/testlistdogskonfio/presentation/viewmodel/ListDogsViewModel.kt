package com.example.testlistdogskonfio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlistdogskonfio.common.DataState
import com.example.testlistdogskonfio.data.model.ListDogsModel
import com.example.testlistdogskonfio.domain.ListDogsCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListDogsViewModel @Inject constructor(private val listDogsCase : ListDogsCaseImpl): ViewModel() {

    private val _response = MutableStateFlow<DataState<ListDogsModel>>(DataState.Loading)
    val getResponse: StateFlow<DataState<ListDogsModel>>
        get() = _response

    fun getListDogs() {
        viewModelScope.launch {
            listDogsCase.getListDogs().collect { state ->
                _response.value = state
            }
        }
    }
}
