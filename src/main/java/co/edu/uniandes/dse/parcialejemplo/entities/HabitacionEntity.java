package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Getter
@Setter
@Entity
public class HabitacionEntity extends BaseEntity {
    private int identificacion;
    private int personas;
    private int num_camas;
    private int num_baños;

    @PodamExclude
	@ManyToOne
	private HotelEntity hotel;
	
	

}
