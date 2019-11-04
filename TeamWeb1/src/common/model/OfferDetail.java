package common.model;

import java.util.ArrayList;
import java.util.List;

import common.dto.Offer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDetail {
	private Offer offer;
	private List<JobWithSkills> jobs = new ArrayList<>();
	
	public JobWithSkills getJobWithSkills(String jobName) {
		return jobs.stream()
				.filter(j->j.getJobName().equals(jobName))
				.findAny().orElse(new JobWithSkills(jobName));
	}
}
