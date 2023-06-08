package com.plcoding.composepaging3caching.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.plcoding.composepaging3caching.data.local.BeerEntity
import com.plcoding.composepaging3caching.network.model.mapper.asDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
): ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map {
                beerEntity: BeerEntity ->  listOf(beerEntity).asDomain()[0]
            }
        }
        .cachedIn(viewModelScope)


}