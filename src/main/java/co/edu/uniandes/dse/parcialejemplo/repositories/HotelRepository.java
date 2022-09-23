package co.edu.uniandes.dse.parcialejemplo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    
    Optional<HotelEntity> findById(Long id);
}
