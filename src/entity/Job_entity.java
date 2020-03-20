package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="metier")
public class Job_entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="metier_Id")
	private Integer jobId;
	
	@Column(name="metier_Nom")
	private String jobName;

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
