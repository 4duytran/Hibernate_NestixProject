package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="artiste")
@PrimaryKeyJoinColumn(name = "artiste_Id")
public class Artist_entity extends Person_entity {

	@Column(name="artiste_Surnom")
	private String surName;
	
	@ManyToMany(mappedBy = "artists", cascade = CascadeType.PERSIST)
    private Set<Media_entity> medias = new HashSet<>();
    
	public Set<Media_entity> getMedias() {
		return medias;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}
	
}
