package com.stream.AddressBook;

import java.util.*;

public class StreamAddressSystem {
	    public Map<String, AddressSystemMain> addressBook = new TreeMap<String,AddressSystemMain>();
	    Scanner sc = new Scanner(System.in);
	    public Map<String, List<String>> cityPersonsMap;
		public Map<String, List<String>> statePersonsMap;

	    
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
		public Map<String, List<String>> dictionaryOfCity_Person()
		{
			System.out.println("Enter the City Name to maintain CITY_PERSONS dictionary :");
			String cityName = sc.nextLine();
			cityPersonsMap = new HashMap<>();
			List<String> cityPerson = new ArrayList<>();
			for(String key : addressBook.keySet())
			{
				AddressSystemMain mainObj = addressBook.get(key);
				List<Contacts> tempList = mainObj.getContactList();
				for (Contacts index : tempList) 
				{
					if (index.getCityName().equalsIgnoreCase(cityName))
					{
						cityPerson.add((index.getFirstName() + " " + index.getLastName()));
						cityPersonsMap.put(cityName, cityPerson);
					}
				}
			}
			return cityPersonsMap ;
		}
		public Map<String, List<String>> dictionaryOfState_Person()
		{
			System.out.println("Enter the State Name to maintain STATE_PERSONS dictionary :");
			String stateName = sc.nextLine();
			statePersonsMap = new HashMap<>();
			List<String> statePerson = new ArrayList<>();
			for(String key : addressBook.keySet())
			{
				AddressSystemMain mainObj = addressBook.get(key);
				List<Contacts> tempList = mainObj.getContactList();
				for (Contacts index : tempList) 
				{
					if (index.getStateName().equalsIgnoreCase(stateName))
					{
						statePerson.add((index.getFirstName() + " " + index.getLastName()));
						statePersonsMap.put(stateName, statePerson);
					}
				}
			}
			return statePersonsMap ;
		}
		public void showCountOfPersonsByCityAndState()
		{
			Set<String> cityKey = dictionaryOfCity_Person().keySet();
			Set<String> stateKey = dictionaryOfState_Person().keySet();
			String city = "" , state = "" ;
			for(String stateObj : cityKey)
			{
				state = stateObj;
				break;
			}
			for(String cityObj : stateKey)
			{
				city = cityObj;
				break;
			}
			System.out.println("Total number of persons in City :" + city + " is :- " + cityKey.size());
			System.out.println("Total number of persons in State :" + state + " is :- " + stateKey.size());
		}
}
