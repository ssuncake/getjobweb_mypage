package common.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Offer {
	int no;
	Date begin_date;
	Date end_date;
	String title;
	String content;
	String ci_img;
	String corporate_id;
}
