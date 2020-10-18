package com.stream.AddressBook;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class TestAddressFileIO {

	@Test
	public void given3Contacts_WrittenToAFile_ShouldMatchContactDeatails() {
		Contacts jeff=new Contacts("Jeff","Bezoss","jeff@gmail.com","San Francisco","Address","120012","California","+23-1234567890");
		Contacts mark=new Contacts("Mark","Zukerberg","mark@gmail.com","New York City","Address","123456","New York","+12-9874563210");
		Contacts satya=new Contacts("Satya", "Nadela","satya@gmail.com","Los Angeles","Address","120546","California","+11-5463217890");
		List<Contacts> contacts=Arrays.asList(new Contacts[] {jeff, mark, satya});
		AddressFileIO addressBookFileIOService=new AddressFileIO("addressBook-test-file.txt");
		addressBookFileIOService.writeContactDetailsInAFile(contacts);
		List<Contacts> readContacts=addressBookFileIOService.readAddressBookFromAFile();
		assertEquals(jeff.toString(),readContacts.get(0).toString());
		assertEquals(mark.toString(),readContacts.get(1).toString());
		assertEquals(satya.toString(),readContacts.get(2).toString());
	}
}
