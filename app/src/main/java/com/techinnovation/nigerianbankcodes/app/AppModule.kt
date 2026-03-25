package com.techinnovation.nigerianbankcodes.app

import android.content.Context
import androidx.room.Room
import com.android.billingclient.api.BillingClient
import com.techinnovation.nigerianbankcodes.core.billing.PlayBillingManager
import com.techinnovation.nigerianbankcodes.core.billing.PremiumManager
import com.techinnovation.nigerianbankcodes.core.data.local.AppDatabase
import com.techinnovation.nigerianbankcodes.core.data.local.ScanDao
import com.techinnovation.nigerianbankcodes.core.data.repository.ScanRepositoryImpl
import com.techinnovation.nigerianbankcodes.core.datastore.PreferencesDataStore
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import com.techinnovation.nigerianbankcodes.core.domain.repository.ScanRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlatformModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "smart-scanner.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideScanDao(database: AppDatabase): ScanDao = database.scanDao()

    @Provides
    @Singleton
    fun provideBillingClient(@ApplicationContext context: Context): BillingClient =
        BillingClient.newBuilder(context)
            .setListener { _, _ -> }
            .enablePendingPurchases()
            .build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindScanRepository(impl: ScanRepositoryImpl): ScanRepository

    @Binds
    abstract fun bindPreferencesRepository(impl: PreferencesDataStore): PreferencesRepository

    @Binds
    abstract fun bindPremiumManager(impl: PlayBillingManager): PremiumManager
}
