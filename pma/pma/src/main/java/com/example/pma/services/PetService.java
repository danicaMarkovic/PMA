package com.example.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pma.domain.Pet;
import com.example.pma.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepo;

	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return petRepo.findAll();
	}

	public List<Pet> findAllByIsFound(boolean b) {
		// TODO Auto-generated method stub
		return petRepo.findAllByIsFound(b);
	}

	public List<Pet> findAllByOwnerId(Long ownerId) {
		// TODO Auto-generated method stub
		return petRepo.findAllByOwnerId(ownerId);
	}
	
}