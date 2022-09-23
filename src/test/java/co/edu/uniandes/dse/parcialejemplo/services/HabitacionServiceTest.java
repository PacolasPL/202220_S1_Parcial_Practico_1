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
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HabitacionService.class)
public class HabitacionServiceTest {
    @Autowired
    private HabitacionService habitacionService;
    @Autowired
    private TestEntityManager entityManager;

    private List<HabitacionEntity> ltHabitaciones = new ArrayList<>();
    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp () {
        clearData();
        insertData();
    }
    private void clearData()
    {
        entityManager.getEntityManager().createQuery("Delete from HabitacionEntity").executeUpdate();
       
    }

    private void insertData()
    {
        for(int i = 0; i < 5; i++){
            HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
            entityManager.persist(habitacionEntity);
            ltHabitaciones.add(habitacionEntity);
        }
        HabitacionEntity habitacionEntity = ltHabitaciones.get(2);
        entityManager.persist(habitacionEntity);
    }
    @Test
    void testCreateHabitacion()throws IllegalOperationException  {
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
        HabitacionEntity result = habitacionService.createHabitacion(newEntity);
        assertNotNull(result);
        HabitacionEntity entity = entityManager.find(HabitacionEntity.class, result.getId());

        assertEquals(newEntity.getIdentificacion(), entity.getIdentificacion());
    }
}
