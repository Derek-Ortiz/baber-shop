package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.ApiService
import ortiz.derek.c4.barber_shop.data.remote.dto.CitasResponse
import javax.inject.Inject

class GetCitasUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(id: Int): CitasResponse {
        return apiService.getCitas(id)
    }
}
