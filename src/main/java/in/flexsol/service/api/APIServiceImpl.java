package in.flexsol.service.api;

import in.flexsol.dao.api.APIDao;
import in.flexsol.modal.gurgaon.GurgaonAirFeed;

import org.springframework.stereotype.Service;

@Service
public class APIServiceImpl implements APIService {

	APIDao apiDao;
	
	@Override
	public int saveAPIDataGurgaon(GurgaonAirFeed response) {
		
		return apiDao.saveAPIDataGurgaon(response);
	}



	

}
