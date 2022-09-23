package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelService {
    @Autowired
    private HotelRepository repository;
    
    
    @Transactional
    public HotelEntity createHotel(HotelEntity hotel) {
        log.info("Inicia proceso de creación de un hotel");
        if ( 2< hotel.getEstrellas() && hotel.getEstrellas()< 6 ){
            return repository.save(hotel);
    } else{
        return null;
    }
}

}

