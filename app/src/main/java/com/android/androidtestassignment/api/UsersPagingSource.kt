package com.android.androidtestassignment.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.androidtestassignment.api.datamodel.UserModel
import kotlinx.coroutines.Dispatchers

class UsersPagingSource(
    val api: UsersRetrofitApi,
) : PagingSource<Int, UserModel>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, UserModel> {
        try {
            val currentPage = params.key ?: INITIAL_PAGE
            val response = with(Dispatchers.IO) {
                api.getUsers(currentPage, PAGE_SIZE, "abc")
            }
            return LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == INITIAL_PAGE) null else currentPage - 1,
                nextKey = if (response.info.results < PAGE_SIZE || response.info.page >= MAX_PAGES) null else currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}

const val INITIAL_PAGE = 1
const val PAGE_SIZE = 20
const val MAX_PAGES = 3