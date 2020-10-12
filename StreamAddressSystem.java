package com.stream.AddressBook;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAddressSystem {
	    static public Map<String, AddressSystemMain> addressBook = new TreeMap<String,AddressSystemMain>();
	    Scanner sc = new Scanner(System.in);
	    static public Map<String, List<Contacts>> cityPersonsMap;
		static public Map<String, List<Contacts>> statePersonsMap;

	    
		public  void showAddressBooks() {
			System.out.println("Do you want to view address book (Y/N-y/n): ");
			char ch=sc.next().charAt(0);
			if(ch=='N' || ch=='n')
			System.out.println("User does not want to view address book.");
			else
				{
					System.out.println("List of Address Books Added: ");
					addressBook.forEach((k,v) -> System.out.println(k + " " + v.contactList + "\n"));
				}
		}
		
		public void viewPersonByCityOrState()
		{
			System.out.println("Do you want to view person by city or state (Y/N-y/n): ");
			char ch=sc.next().charAt(0);
			
			if(ch=='N' || ch=='n')
			System.out.println("User does not want to view person by city or state.");
			else
			{
				System.out.println("Enter the City or State Name to search the person :");
				String cityOrStateName = sc.nextLine(); 
				addressBook.keySet().stream().forEach( key -> {
					AddressSystemMain mainObj = addressBook.get(key);
					mainObj.getContactList().stream().filter(name ->
					name.getStateName().equalsIgnoreCase(cityOrStateName) || name.getCityName().equalsIgnoreCase(cityOrStateName))
							.forEach(person -> System.out.println((person.getFirstName()+" "+person.getLastName()) 
							+ "is present in City or State: " + cityOrStateName));
			});
		}			
	}
	
	
	/**
	 * UC9
	 */
	public void dictionaryOfState_PersonsAndCity_Persons() 
	{
		System.out.println("Enter the City Name to maintain CITY_PERSONS dictionary :");
		String cityName = sc.nextLine();
		System.out.println("Enter the State Name to maintain STATE_PERSONS dictionary :");
		String stateName = sc.nextLine();
		cityPersonsMap = new HashMap<>();
		statePersonsMap = new HashMap<>();
		addressBook.keySet().stream().forEach( key -> {
			AddressSystemMain mainObj = addressBook.get(key);
			List<Contacts> cityPerson = mainObj.getContactList().stream()
					.filter(contact -> contact.getCityName().equals(cityName)).collect(Collectors.toList());
			cityPersonsMap.put(cityName, cityPerson);
		});
		
		addressBook.keySet().stream().forEach( key -> {
			AddressSystemMain mainObj = addressBook.get(key);
			List<Contacts> statePerson = mainObj.getContactList().stream()
					.filter(contact -> contact.getStateName().equals(stateName)).collect(Collectors.toList());
			statePersonsMap.put(stateName, statePerson);
		});
	}
	
	/**
	 * UC10
	 */
	public static void showCountOfPersonsByCityAndState()
	{
		addressBook.keySet().stream().forEach(key -> {
			AddressSystemMain mainObj = addressBook.get(key);
			System.out.println("In the address book " + key);
			System.out.println("Persons Count by City");
			StreamAddressSystem.cityPersonsMap.keySet().stream().forEach(
					cityName -> System.out.println(cityName + ": " + StreamAddressSystem.cityPersonsMap.get(cityName).size()));
			System.out.println("Persons Count by State:");
			StreamAddressSystem.statePersonsMap.keySet().stream().forEach(
					stateName -> System.out.println(stateName + ": " + StreamAddressSystem.statePersonsMap.get(stateName).size()));
		});
	}
}
