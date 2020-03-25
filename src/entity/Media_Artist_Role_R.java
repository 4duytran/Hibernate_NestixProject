package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="role_artiste")
public class Media_Artist_Role_R implements Serializable{

	
	private static final long serialVersionUID = -2556679331951600055L;

	@Id
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "media_Id")
	private Media_entity medias;
	
	@Id
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "metier_Id")
	private Job_entity jobs;
	
	@Id
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "artiste_Id")
	private Artist_entity artists;

	public Media_entity getMedias() {
		return medias;
	}

	public void setMedias(Media_entity medias) {
		this.medias = medias;
	}

	public Job_entity getJobs() {
		return jobs;
	}

	public void setJobs(Job_entity jobs) {
		this.jobs = jobs;
	}

	public Artist_entity getArtists() {
		return artists;
	}

	public void setArtists(Artist_entity artists) {
		this.artists = artists;
	}
}
