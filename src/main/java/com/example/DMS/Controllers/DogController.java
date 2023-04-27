/**
 * 
 */
package com.example.DMS.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.DMS.Models.Dog;
import com.example.DMS.Models.Trainer;
import com.example.DMS.repository.DogRepository;
import com.example.DMS.repository.TrainerRepository;



/**
 * @author Bhanuteja Chitrala
 *
 */
@Controller
public class DogController {
	
	ModelAndView mav=new ModelAndView();
	
	@Autowired
	DogRepository dogRepo;
	
	@Autowired
	TrainerRepository trainerRepo;

//	@RequestMapping("dogHome")
//	public String home() {
//		return "home";
//	}
	
	
	@RequestMapping("dogHome")
	public ModelAndView home() {
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("add")
	public ModelAndView add() {
		mav.setViewName("addNewDog");
		mav.addObject("trainers",trainerRepo.findAll());
		return mav;
	}
	
	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog,@RequestParam("trainerId") int trainerId) {
		
		Trainer trainer=trainerRepo.findById(trainerId).orElse(new Trainer());
		
		dog.setTrainer(trainer);
		dogRepo.save(dog);
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("addTrainer")
	public ModelAndView addTrainer() {
		mav.setViewName("addNewTrainer");
		return mav;
	}
	
	@RequestMapping("trainerAdded")
	public ModelAndView addNewTrainer(Trainer trainer) {
		trainerRepo.save(trainer);
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs() {
		mav.setViewName("viewDogs");
		Iterable<Dog> dogList= dogRepo.findAll();
		mav.addObject("dogs", dogList);
		return mav;
	}
	
	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog) {
		dogRepo.save(dog);
		mav.setViewName("editDog");
		return mav;
	}
	
	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog) {
		 
//		Optional<Dog> dogFound= dogRepo.findById(dog.getId());   
//		if(dogFound.isPresent()) 
//		{
//			dogRepo.delete(dog);
//		}
		
//		List<Dog> dogs= dogRepo.findByName(dog.getName());
//		
//		for(Dog d:dogs) {
//			dogRepo.delete(d);
//		}
		
		Dog d=dogRepo.findById(dog.getId()).orElse(new Dog());
		dogRepo.delete(d);
		
		return home();
	}
	
	@RequestMapping("search")
	public ModelAndView searchById(int id) {
		Dog dog = dogRepo.findById(id).orElse(new Dog());
		mav.addObject(dog);
		mav.setViewName("searchResults");
		return mav;
	}
	
	
	
	
	
}
