package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="artiste")
@PrimaryKeyJoinColumn(name = "artiste_Id")
public class Artist_entity extends Person_entity {

	@Column(name="artiste_Surnom")
	private String surName;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "role_artiste", joinColumns = {
	        @JoinColumn(name = "artiste_Id")},
	        inverseJoinColumns = {@JoinColumn(name = "media_Id")})
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
	
	public void addMedia(Media_entity media) {
        medias.add(media);
        media.getArtists().add(this);
    }
	
}
