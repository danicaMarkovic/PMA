package com.example.pma;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pma.domain.Address;
import com.example.pma.domain.Comment;
import com.example.pma.domain.Pet;
import com.example.pma.domain.PetGender;
import com.example.pma.domain.PetType;
import com.example.pma.domain.User;
import com.example.pma.services.AddressService;

import com.example.pma.services.CommentService;
import com.example.pma.services.PetService;
import com.example.pma.services.UserService;

@Component
public class FillData {
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AddressService addressService;

	@Autowired
	private CommentService commentService;

	@PostConstruct
    public void init() throws URISyntaxException, IOException, ParseException {
		
		//USERS
		User user = new User("jova@gmail.com", "123", false);
		userService.saveUser(user);
		
		User user1 = new User("pera@gmail.com", "123", false);
		userService.saveUser(user1);
		
		User user2 = new User("marko@gmail.com", "123", false);
		userService.saveUser(user2);
		
		User user3 = new User("milica@gmail.com", "123", false);
		userService.saveUser(user3);
		
		User user4 = new User("jelena@gmail.com", "123", false);
		userService.saveUser(user4);
		
		User user5 = new User("dragana@gmail.com", "123", false);
		userService.saveUser(user5);
		
		//ADDRESS
		Address a1 = new Address("Novi Sad", "Stevana Mokranjca","1", 19.8178678, 45.2586882);
		addressService.add(a1);
		Address a2 = new Address("Zrenjanin", "Cara Dusana","1", 20.3881148, 45.3823109);
		addressService.add(a2);
		Address a3 = new Address("Novi Sad", "Mise Dimitrijevica","1", 19.8326309, 45.2478133);
		addressService.add(a3);
		Address a4 = new Address("Novi Sad", "Micurinova","1", 19.8259408, 45.2530566);
		addressService.add(a4);
		Address a5 = new Address("Novi Sad", "Radnicka","1", 19.8444193, 45.2488004);
		addressService.add(a5);
		Address a6 = new Address("Novi Sad", "Mose Pijade","1", 19.854462, 45.222700);
		addressService.add(a6);
		Address a7 = new Address("Novi Sad", "Svetozara Miletica","1", 19.854326,45.223552);
		addressService.add(a7);
		Address a8 = new Address("Novi Sad", "Vladimira Nazora","1",  19.855238, 45.225298);
		addressService.add(a8);
		
		
		//PETS
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-10");
		Pet pet = new Pet(PetType.DOG,"Dzeki",PetGender.MALE, "Pas ima zelenu ogrlicu", "puppydog.jpg", date, "123-456", false, user, a1);
		petService.addNewPet(pet);
		
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01");
		Pet pet1 = new Pet(PetType.DOG,"Aleks",PetGender.MALE, "Opis neki", "labrador.jpg", date1, "123-456", false, user1, a2);
		petService.addNewPet(pet1);
		
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-12");
		Pet pet2 = new Pet(PetType.CAT,"Djura",PetGender.MALE, "Ne prilazi nepoznatima", "cat.jpg", date2, "123-456", false, user2, a3);
		petService.addNewPet(pet2);
		
		Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-29");
		Pet pet3 = new Pet(PetType.CAT,"Kiki",PetGender.FEMALE, "Ruska plava macka", "russiancat.jpg", date3, "021/444-444", false, user, a4);
		petService.addNewPet(pet3);
		
		Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-10");
		Pet pet4 = new Pet(PetType.DOG,"Bobi",PetGender.MALE, "Drustven, prilazi deci", "samojedjpg.jpg", date4, "021/123-456", false, user3, a5);
		petService.addNewPet(pet4);
		
		Date date5 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-29");
		Pet pet5 = new Pet(PetType.DOG,"Moksi",PetGender.FEMALE, "Laje ali ne ujeda", "pup.jpg", date5, "123-456", false, user4,a6);
		petService.addNewPet(pet5);
		
		Date date6 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-14");
		Pet pet6 = new Pet(PetType.DOG,"Reks",PetGender.MALE, "Vucjak, star godinu dana, pobegao u blizini marketa", "download.jpg", date6, "123456789", false, user5, a7);
		petService.addNewPet(pet6);
		
		Date date7 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-15");
		Pet pet7 = new Pet(PetType.DOG,"Mona",PetGender.FEMALE, "Pas ima crvenu ogrlicu sa kodom", "dalmatian.jpg", date7, "123456789", false, user2, a8);
		petService.addNewPet(pet7);
		
		
		//COMMENTS
		Comment com1 = new Comment("Video sam ga na uglu bulevara Lazara", new Date(), user, pet);
		commentService.save(com1);
		
        Comment com2 = new Comment("Bas je lep!", new Date(), user1 ,pet);
        commentService.save(com2);
        
        Comment com3 = new Comment("Nisam ga video. Primetio sam da ima mnogo lutalica u Novom Sadu.", new Date(), user2, pet);
        commentService.save(com3);
        
        Comment com4 = new Comment("Lep je", new Date(),user3 ,pet1);
        commentService.save(com4);
        
        Comment com5 = new Comment("Nadam se da cete ga pronaci!", new Date(), user1, pet1);
        commentService.save(com5);
        
	}
}
