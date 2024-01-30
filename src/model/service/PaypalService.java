package model.service;

public class PaypalService implements OnlinePaymentService{
	public double paymentFee(Double amount) {
		return amount * 0.02;
	}
	
	public double interest(Double amount,Integer months) {
		return amount * 0.01 * months;
	}
}
