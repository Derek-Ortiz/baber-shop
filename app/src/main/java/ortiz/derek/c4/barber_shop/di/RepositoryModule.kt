package ortiz.derek.c4.barber_shop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ortiz.derek.c4.barber_shop.data.repository.AdminRepositoryImpl
import ortiz.derek.c4.barber_shop.data.repository.BarberShopRepositoryImpl
import ortiz.derek.c4.barber_shop.data.repository.ServicioRepositoryImpl
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import ortiz.derek.c4.barber_shop.domain.repository.ServicioRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAdminRepository(impl: AdminRepositoryImpl): AdminRepository

    @Binds
    @Singleton
    abstract fun bindBarberShopRepository(impl: BarberShopRepositoryImpl): BarberShopRepository

    @Binds
    @Singleton
    abstract fun bindServicioRepository(impl: ServicioRepositoryImpl): ServicioRepository
}