package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="artiste")
@PrimaryKeyJoinColumn(name = "artiste_Id")
public class Artist_entity extends Person_entity {

	@Column(name="artiste_Surnom")
	private String surName;
	
	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}
	
}
