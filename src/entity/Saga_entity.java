package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="saga")
public class Saga_entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer saga_Id;
	
	@Column(name="saga_Nom")
	private String saga_Name;

	public Integer getSaga_Id() {
		return saga_Id;
	}

	public void setSaga_Id(Integer saga_Id) {
		this.saga_Id = saga_Id;
	}

	public String getSaga_Name() {
		return saga_Name;
	}

	public void setSaga_Name(String saga_Name) {
		this.saga_Name = saga_Name;
	}

}
