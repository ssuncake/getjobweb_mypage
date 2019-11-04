package common.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestSchedule {
	private String id;
	private String cert_name; 
	private Date begin_date; 
	private Date end_date;
}
