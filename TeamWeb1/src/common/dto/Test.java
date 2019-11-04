package common.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Test {
	private String cert_name;
	private Date begin_date = new Date();
	private Date end_date = new Date();
	private String section;
	
}
