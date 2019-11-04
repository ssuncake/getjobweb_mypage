package common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class JobWithSkills {
	private String jobName;
	private List<String> skills = new ArrayList<>();

	public JobWithSkills(String jobName) {
		super();
		this.jobName = jobName;
	}

	public void addSkill(String skill) {
		skills.add(skill);
	}

	public void removeSkill(String skill) {
		skills.remove(skill);
	}
}
