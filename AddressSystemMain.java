package com.stream.AddressBook;
import java.util.*;
import java.io.*;
public class AddressSystemMain {
	
		public  static  List<Contacts> contactList = new ArrayList<>();
		public static Map<String, Contacts > addressMap = new HashMap<>();
	    static Scanner sc = new Scanner(System.in);
		public List<Contacts> getContactList() {
			return contactList;
		}

		public void setContactList(List<Contacts> list) {
			this.contactList = list;
		}
		public void addContact()
		{
			System.out.println("Enter the number of persons whose contact details are to be added: ");
			int noOfPersons=sc.nextInt();
			sc.nextLine();
			for(int i=0;i<noOfPersons;i++)
			{
					System.out.println("Enter Contact Details of Person "+(i+1)+" :");
					System.out.println("Enter First Name: ");
					String fn=sc.nextLine();
					System.out.println("Enter Last Name: ");
					String ln=sc.nextLine();
					System.out.println("Enter Address: ");
					String add=sc.nextLine();
					System.out.println("Enter City: ");
					String c=sc.nextLine();
					System.out.println("Enter State: ");
					String s=sc.nextLine();
					System.out.println("Enter Zip Code: ");
					String zc=sc.nextLine();
					System.out.println("Enter Phone Number: ");
					String pn=sc.nextLine();
					System.out.println("Enter EmailId: ");
					String ei=sc.nextLine();
					Contacts contactObj = new Contacts(fn,ln,ei,c,add,zc,s,pn);
					contactList.add(contactObj);
					addressMap.put(contactObj.getFirstName(), contactObj);
			}
		}
		
		public void editContact()
		{
			int x;
			boolean flag = true;
			System.out.println("Do you want to edit contact details (Y/N-y/n): ");
			char ch=sc.next().charAt(0);
			sc.nextLine();
			while(flag)
			{
				if(ch=='N' || ch=='n')
				{	
					System.out.println("User does not want to edit contact details.");
					break;
				}
				
				System.out.println("Enter the first name whose contact details is to be edited: ");
				String editN = sc.nextLine();
				x=0;
				for(int i = 0;i<contactList.size();i++)
				{
					if((ch=='Y' || ch=='y') && contactList.get(i).getFirstName().equals(editN))
					{
						System.out.println("Enter New Details: ");
						System.out.println("Enter First Name: ");
						String fn=sc.nextLine();
						System.out.println("Enter Last Name: ");
						String ln=sc.nextLine();
						System.out.println("Enter Address: ");
						String add=sc.nextLine();
						System.out.println("Enter City: ");
						String c=sc.nextLine();
						System.out.println("Enter State: ");
						String s=sc.nextLine();
						System.out.println("Enter Zip Code: ");
						String zc=sc.nextLine();
						System.out.println("Enter Phone Number: ");
						String pn=sc.nextLine();
						System.out.println("Enter EmailId: ");
						String ei=sc.nextLine();
						Contacts contactObj = new Contacts(fn,ln,ei,c,add,zc,s,pn);
						contactList.set(i, contactObj);
						System.out.println("Contact details of "+editN+" edited successfully! Updated Details are: ");
						for(int j=0;j<contactList.size();j++)
						{
							System.out.println("Contact Details of Person "+(j+1)+" :");
							System.out.println(contactList.get(j));
						}
						x=1;
						flag= false;
						break;
					}
				}
				if(x==0 && (ch=='Y' || ch=='y'))
					System.out.println("User contact details not available.Please enter correct first name.");	
			}
		}
		public void removeContact() {
			boolean flag = true;
			int x;
			System.out.println("Do you want to remove contact details (Y/N-y/n): ");
			char ch=sc.next().charAt(0);
			sc.nextLine();
			while(flag)
			{
				if(ch=='N' || ch=='n')
				{	
					System.out.println("User does not want to remove contact details.");
					break;
				}
				System.out.println("Enter the first name whose contact details is to be removed: ");
				String removeN = sc.nextLine();
				x=0;
				for(Contacts l : contactList)
				{
					if((ch=='Y' || ch=='y') && l.getFirstName().equals(removeN))
					{
						contactList.remove(x);
						System.out.println("Contact Details removed successfully!! Updated Details are: ");
						for(int j=0;j<contactList.size();j++)
						{
							System.out.println("Contact Details of Person "+(j+1)+" :");					
							System.out.println(contactList.get(j));
						}
						x=1;
						flag = false;
						break;
					}
				}
				if(x==0 && (ch=='Y' || ch=='y'))
					System.out.println("User contact details not available.Please enter correct first name.");
			}			
		}
		public void checkDuplicateEntryOfName()
		{
			long counter= 0;
			System.out.println("Enter first and last name to check for repeatition :");
			String firstName = sc.nextLine();
			String lastName = sc.nextLine();
			counter = contactList.stream().filter( (conObj) ->  (conObj.getFirstName() +" " + conObj.getLastName()).equals(firstName + " " + lastName)).count();
		    if(counter==0)
				System.out.println("Duplicate entry does not exist.");
			else
				System.out.println("Already a person with similar details exists.");
		}
		public static void main(String[] args) {
			AddressSystemMain mainObj = new AddressSystemMain();
			StreamAddressSystem addressObj = new StreamAddressSystem();
			while(true)
			{
				System.out.println("Enter name of the Address Book");
				String addressBookName = sc.nextLine();
				if(addressObj.addressBook.containsKey(addressBookName))
					System.out.println("Address Book with same name already exists");
				else {
					System.out.println("Enter Details for " + addressBookName);
					mainObj.addContact();
					addressObj.addressBook.put(addressBookName,mainObj);
				}
				System.out.println("Do you want to add more Address Books (y/n)");
				if(sc.next().equals("y"))
					continue;
				else
					break;
			}
			for(int j=0;j<contactList.size();j++)
			{
				System.out.println("Contact Details of Person "+(j+1)+" :");
				System.out.println(contactList.get(j));
			}
			mainObj.editContact();
			mainObj.removeContact();
			addressObj.showAddressBooks();
			mainObj.checkDuplicateEntryOfName();
			addressObj.viewPersonByCityOrState();
			addressObj.dictionaryOfCity_Person();
			addressObj.dictionaryOfState_Person();
			addressObj.showCountOfPersonsByCityAndState();
		}
}
