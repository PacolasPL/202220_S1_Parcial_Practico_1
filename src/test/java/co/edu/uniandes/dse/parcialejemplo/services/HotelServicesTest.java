package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelService.class)
public class HotelServicesTest {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private TestEntityManager entityManager;

    private List<HotelEntity> ltHotel = new ArrayList<>();
    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp () {
        clearData();
        insertData();
    }
    private void clearData()
    {
        entityManager.getEntityManager().createQuery("Delete from HotelEntity").executeUpdate();
       
    }

    private void insertData()
    {
        for(int i = 0; i < 5; i++){
            HotelEntity hotelEntity = factory.manufacturePojo(HotelEntity.class);
            entityManager.persist(hotelEntity);
            ltHotel.add(hotelEntity);
        }
        HotelEntity hotelEntity = ltHotel.get(2);
        entityManager.persist(hotelEntity);
    }
    @Test
    void testCreateCreador()  {
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
        HotelEntity result = hotelService.createHotel(newEntity);
        assertNotNull(result);
        HotelEntity entity = entityManager.find(HotelEntity.class, result.getId());

        assertEquals(newEntity.getId(), entity.getId());
    }
}
