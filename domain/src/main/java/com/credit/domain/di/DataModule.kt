package com.credit.domain.di

import com.credit.domain.data.remote.HomeRemoteDataSourceImpl
import com.credit.domain.data.repository.HomeRepositoryImpl
import com.credit.domain.data.session.SessionManagerImpl
import com.credit.domain.datasource.HomeRemoteDataSource
import com.credit.domain.repository.HomeRepository
import com.credit.domain.session.SessionManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataSource(impl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindSessionManager(impl: SessionManagerImpl): SessionManager
}
