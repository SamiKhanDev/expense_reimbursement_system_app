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
        .baseUrl("https://dccd-2400-adc5-45a-3f00-4012-8d11-6776-5ec4.ngrok-free.app") // Replace with your server's base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideExpenseApi(retrofit: Retrofit): ExpenseApi =
        retrofit.create(ExpenseApi::class.java)
}
