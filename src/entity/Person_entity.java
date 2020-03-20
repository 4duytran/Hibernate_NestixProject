package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "personne")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person_entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="personne_Id")
	private Integer p_id;
	
	@Column(name="personne_Nom")
	private String firstName;
	
	@Column(name="personne_Prenom")
	private String lastName;
	
	@Column(name="personne_DateNaiss")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
}
