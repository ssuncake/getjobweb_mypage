package common.model;

import java.util.HashMap;
import java.util.Map;

public class MemberRecomOffers {
	private Map<Integer, String> offerWithType = new HashMap<>();
	
	public void addOffer(Integer offer, String type) {
		offerWithType.put(offer, type);
	}
	
	public void removeOffer(Integer offer) {
		offerWithType.remove(offer);
	}
}
