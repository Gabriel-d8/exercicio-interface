package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental details:");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.println("Date of withdrawal (dd/mm/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.println("Return date (dd/mm/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel)); 
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("Invoice: ");
		System.out.println("Basic payment: " + "R$" + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax Service: " + "R$" + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: " + "R$" + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
	}

}