package com.stream.AddressBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AddressFileIO {
	public String addressBookName;
	public AddressFileIO(String addressBookName) {
		super();
		this.addressBookName = addressBookName;
	}
	
	public List<Contacts> readAddressBookFromAFile() {
		List<Contacts> list = new ArrayList<>();
		try {
				Files.lines(Paths.get("addressBookFile.txt"))
						.forEach(lines -> {
							String[] contactsArray=lines.split(",");
								String firstName = contactsArray[0];
								String lastName = contactsArray[1];
								
								String email = contactsArray[2];
								
								String city = contactsArray[3];
								
								String address = contactsArray[4];
								
								String zip = contactsArray[5];
								
								String state = contactsArray[6];
								
								String phone = contactsArray[7];
								Contacts contactObj = new Contacts(firstName, lastName,email, city,address, zip,state, phone);
								list.add(contactObj);
						});
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void writeContactDetailsInAFile(List<Contacts> list) {
		StringBuffer contactsBuffer=new StringBuffer();
		list.forEach(contact-> contactsBuffer.append(contact.toString().concat("\n")));
		try {
			Files.write(Paths.get("addressBookFile.txt"),contactsBuffer.toString().getBytes());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
