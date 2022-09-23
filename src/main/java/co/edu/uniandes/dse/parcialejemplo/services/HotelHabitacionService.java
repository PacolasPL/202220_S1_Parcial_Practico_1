package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelHabitacionService {
    @Autowired
    private HotelRepository repositoryHotel;
    
    @Autowired
    private HabitacionRepository repositoryHabitacion;
    
    
    @Transactional
    public HabitacionEntity addHabitacion(Long habitacionId, Long HotelId) throws EntityNotFoundException
    {
        log.info("Inicia el proceso de asociarle una habitacion al hotel de id = {0}", HotelId);
        Optional<HotelEntity> hotelEntity = repositoryHotel.findById(HotelId);  
        Optional<HabitacionEntity> habitacionEntity =  repositoryHabitacion.findById(habitacionId);

        if(hotelEntity.isEmpty())
        {
            throw new EntityNotFoundException("El hotel con el id = {" + Long.toString(HotelId) + "} no existe.");
        }

        if(habitacionEntity.isEmpty())
        {
            throw new EntityNotFoundException("La habitacion con el id = {" + Long.toString(habitacionId) + "} no existe.");
        }

        habitacionEntity.get().setHotel(hotelEntity.get());
        hotelEntity.get().getHabitaciones().add(habitacionEntity.get());
        log.info("Termina el proceso de asociarle a una marca a la ciudad de id = {0}", HotelId);
        return habitacionEntity.get();
    }
}



