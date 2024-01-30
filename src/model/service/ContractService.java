package model.service;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	private Integer months;
	private Contract contract;
	
	OnlinePaymentService service;
	
	public ContractService(){
	}
	
	public ContractService(Integer months, Contract contract, OnlinePaymentService service) {
		this.months = months;
		this.contract = contract;
		this.service = service;
	}
	
	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public OnlinePaymentService getService() {
		return service;
	}

	public void setService(OnlinePaymentService service) {
		this.service = service;
	}

	public void processContract(Contract contract,Integer months){
		double amount = contract.getTotalValue()/months;
		double amountb = amount;
		
		for(int i = 1; i <= months; i ++ ) {
			amountb = amount + service.interest(amount, i);
			amountb = amountb + service.paymentFee(amountb);
			contract.getInstallment().add(new Installment(contract.getDate().plusMonths(i),amountb));
		}		
	}
	
}
