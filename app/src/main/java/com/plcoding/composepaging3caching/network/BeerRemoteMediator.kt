package com.plcoding.composepaging3caching.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.plcoding.composepaging3caching.data.local.BeerDatabase
import com.plcoding.composepaging3caching.data.local.BeerEntity
import com.plcoding.composepaging3caching.network.model.mapper.asEntity
import com.plcoding.composepaging3caching.network.service.ApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDatabase: BeerDatabase,
    private val apiService: ApiService
):RemoteMediator<Int,BeerEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ):MediatorResult {
       return   try {
           val loadKey = when(loadType){
               LoadType.REFRESH -> 1
               LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
               LoadType.APPEND -> {
                   val lstItem = state.lastItemOrNull()
                   if(lstItem == null){
                       1
                   }
                   else{
                       (lstItem.id/state.config.pageSize) + 1
                   }
               }
           }

           val beerResponse = apiService.getBeers(
               page = loadKey,
               per_page = state.config.pageSize
           )

           beerDatabase.withTransaction {
               if(loadType == LoadType.REFRESH) {
                   //beerDatabase.dao.clearAll()
               }
               val beerEntities = beerResponse.map {beer->
                   beerDatabase.BeerDao().insertBeerList(beerResponse.asEntity())
               }
           }

           MediatorResult.Success(
               endOfPaginationReached = beerResponse.isEmpty()
           )
       }
       catch (e:IOException){MediatorResult.Error(e)}
        catch (e:HttpException){MediatorResult.Error(e)}
    }


}