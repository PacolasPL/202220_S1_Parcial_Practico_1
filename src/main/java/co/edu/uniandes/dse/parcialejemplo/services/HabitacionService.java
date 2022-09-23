package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository repository;
    
    
    @Transactional
    public HabitacionEntity createHabitacion(HabitacionEntity habitacion) throws IllegalOperationException{
        log.info("Inicia proceso de creación de una habitacion");
        if ( habitacion.getNum_baños( )<=  habitacion.getPersonas() ){
            return repository.save(habitacion);
    } else{
        throw new IllegalOperationException("No se puede hacer eso");
    }
}

}

