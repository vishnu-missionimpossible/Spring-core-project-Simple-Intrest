package in.ineuron.service;

import in.ineuron.bo.CustomerBO;
import in.ineuron.dao.ICustomerDAO;
import in.ineuron.dto.CustomerDTO;

public class CustomerMgmtServiceImpl implements ICustomerMgmtService {

	private ICustomerDAO dao;

	static {
		System.out.println("CustomerMgmtServiceImpl.class file is loading...");
	}

	public CustomerMgmtServiceImpl(ICustomerDAO dao) {
		System.out.println("CustomerMgmtServiceImpl:: One param constructor..");
		this.dao = dao;
	}

	@Override
	public String calculateSimpleInterest(CustomerDTO dto) {
		Float si = null;
		si = (dto.getPamt()*dto.getTime()*dto.getRate())/100;
		CustomerBO bo = new CustomerBO();
		bo.setCustomerAddress(dto.getCustomerAddress());
		bo.setCustomerName(dto.getCustomerName());
		bo.setInterestAmt(si);
		bo.setPamt(dto.getPamt());
		bo.setRate(dto.getRate());
		bo.setTime(dto.getTime());
		int count = dao.insert(bo);
		if(count==1)
			return "Customer Registration success :: " + dto.getPamt() + " Interest :: " + si;
		else
			return "Customer Registration failed :: " + dto.getPamt() + " Interest :: " + si;
	}

}
