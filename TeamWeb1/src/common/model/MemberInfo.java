package common.model;

import java.util.ArrayList;
import java.util.List;

import common.dto.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfo {
    private Member member;
    private List<String> jobs = new ArrayList<>();
    private List<String> skills = new ArrayList<>();
    
    public void addJob(String job) {
    	jobs.add(job);
    }
    
    public void removeJob(String job) {
    	jobs.remove(job);
    }
    
    public void addSkill(String skill) {
    	skills.add(skill);
    }

    public void removeSkill(String skill) {
    	skills.remove(skill);
    }
}
