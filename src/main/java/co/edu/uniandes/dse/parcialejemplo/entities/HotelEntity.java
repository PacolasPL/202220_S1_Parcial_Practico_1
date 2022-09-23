package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Getter
@Setter
@Entity
public class HotelEntity extends BaseEntity{
    private String Nombre ;
	private String Direccion;
	private int Estrellas;

    @PodamExclude
    @OneToMany( mappedBy="hotel", cascade = CascadeType.PERSIST)
    private List<HabitacionEntity> habitaciones =  new ArrayList<>();

}
