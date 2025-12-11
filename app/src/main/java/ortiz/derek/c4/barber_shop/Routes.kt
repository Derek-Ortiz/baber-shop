package ortiz.derek.c4.barber_shop

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")

    // Barber routes
    object BarberHome : Routes("barber_home")
    object BarberService : Routes("barber_service")
    object BarberRegister : Routes("barber_register")
    object BarberProfile : Routes("barber_profile")
    object BarberShopHome : Routes("barber_shop_home")
    object BarberAppointmentsHistory : Routes("barber_appointments_history")

    // Client routes
    object Home : Routes("home")
    object Services : Routes("services/{barberiaId}") {
        fun createRoute(barberiaId: Int) = "services/$barberiaId"
    }
    object MyAppointments : Routes("my_appointments")
    object Profile : Routes("profile")
    object DateTime : Routes("date_time/{servicioId}") {
        fun createRoute(servicioId: Int) = "date_time/$servicioId"
    }
}