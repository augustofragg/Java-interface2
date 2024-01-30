package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		System.out.println("Entre os dados do contrado: ");
		System.out.print("Numero: ");
		Integer number = sc.nextInt();
		sc.nextLine();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(),dtf );
		System.out.print("Valor do contrato: ");
		Double totalValue = sc.nextDouble();
		sc.nextLine();
		System.out.print("Entre com o numero de parcelas: ");
		Integer months = sc.nextInt();
		
		Contract contract = new Contract(number, date, totalValue);
		ContractService service = new ContractService(new PaypalService());
		
		service.processContract(contract,months);
		
		System.out.println("Parcelas: ");	
		for(Installment ints : contract.getInstallments()) {
			System.out.printf("%s - %.2f%n",ints.getDueDate().format(dtf),ints.getAmount());
		}

			
		sc.close();
	}
}
