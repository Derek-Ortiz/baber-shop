package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.CreateHorarioRequest
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class CreateHorarioUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(createHorarioRequest: CreateHorarioRequest) = repository.createHorario(
        dia = createHorarioRequest.dia,
        horaApertura = createHorarioRequest.horaApertura,
        horaCierre = createHorarioRequest.horaCierre,
        negocioId = createHorarioRequest.negocioId
    )
}
