package com.android.androidtestassignment

import com.android.androidtestassignment.api.UsersRetrofitApi
import com.android.androidtestassignment.repositories.UsersRepository
import com.android.androidtestassignment.repositories.UsersRepositoryImpl
import com.android.androidtestassignment.usecases.GetUsersUseCase
import com.android.androidtestassignment.usecases.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun createApiService(retrofit: Retrofit): UsersRetrofitApi {
        return retrofit.create(UsersRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun createRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl(" https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
@Module
@InstallIn(ViewModelComponent::class)
abstract class UsersModule {
    @Binds
    abstract fun bindGetUsersUseCase(
        getUsersUseCaseImpl: GetUsersUseCaseImpl
    ): GetUsersUseCase
    @Binds
    abstract fun bindUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository
}