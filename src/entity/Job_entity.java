package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="metier")
public class Job_entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="metier_Id")
	private Integer jobId;
	
	@Column(name="metier_Nom")
	private String jobName;

	@OneToMany(mappedBy = "jobs", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Media_Artist_Role_R> media_role_artist;
    
	public Set<Media_Artist_Role_R> getMedia_role_artist() {
		return media_role_artist;
	}

	public void setMedia_role_artist(Set<Media_Artist_Role_R> media_role_artist) {
		this.media_role_artist = media_role_artist;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
}
