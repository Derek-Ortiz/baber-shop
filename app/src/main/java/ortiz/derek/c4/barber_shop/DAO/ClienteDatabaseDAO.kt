package ortiz.derek.c4.barber_shop.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ortiz.derek.c4.barber_shop.Models.Cliente

@Dao
interface ClienteDatabaseDAO {

    @Query("SELECT * from cliente")
    fun getClientes(): Flow<List<Cliente>>

    @Query("SELECT * FROM cliente WHERE id = :id")
    fun getClienteById(id: Int): Flow<Cliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: Cliente)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCliente(cliente: Cliente)

    @Delete
    suspend fun deleteCliente(cliente: Cliente)
}