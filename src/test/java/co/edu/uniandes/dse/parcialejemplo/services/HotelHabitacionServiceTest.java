package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelHabitacionService.class)
public class HotelHabitacionServiceTest {
    @Autowired
    private HotelHabitacionService hotelHabitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HotelEntity> hoteles = new ArrayList<>();

    private List<HabitacionEntity> habitaciones = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        clearData();
        insertData();
    }

    private void clearData()
    {
        entityManager.getEntityManager().createQuery("Delete from CiudadEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("Delete from MarcaEntity").executeUpdate();
    }

    private void insertData()
    {
        for(int i = 0; i < 5; i++){
            HotelEntity hotelEntity = factory.manufacturePojo(HotelEntity.class);
            entityManager.persist(hotelEntity);
            hoteles.add(hotelEntity);
        }
        for(int i = 0; i < 5; i++)
        {
            HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
            entityManager.persist(habitacionEntity);
            habitaciones.add(habitacionEntity);
        }
        HotelEntity hotelEntity = hoteles.get(2);
        HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
        entityManager.persist(habitacionEntity);
        habitacionEntity.setHotel(hotelEntity);
        hotelEntity.getHabitaciones().add(habitacionEntity);
    }
    @Test
    void testAddHabitacion() throws EntityNotFoundException
    {
        HotelEntity newEntity = hoteles.get(2);
        HabitacionEntity habitacion = habitaciones.get(2);
        HabitacionEntity result = hotelHabitacionService.addHabitacion( habitacion.getId(),newEntity.getId());
        assertNotNull(result);
        assertEquals(result.getId(), habitacion.getId());
    }
}
