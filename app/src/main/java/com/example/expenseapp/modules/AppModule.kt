package com.example.expenseapp.modules

import com.example.expenseapp.apis.ExpenseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("   https://4ace-103-177-240-138.ngrok-free.app ") // Replace with your server's base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideExpenseApi(retrofit: Retrofit): ExpenseApi =
        retrofit.create(ExpenseApi::class.java)

}
