package mykiddrawing.quiz.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mykiddrawing.quiz.exception.ResourceNotFoundException;
import mykiddrawing.quiz.model.Coins;
import mykiddrawing.quiz.model.User;
import mykiddrawing.quiz.repository.CoinsRepository;
import mykiddrawing.quiz.repository.UserRepository;



@CrossOrigin(origins="*")
@RestController

@RequestMapping("/api/v1")
public class CoinsController {
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	CoinsRepository coinRepository;
	
	//create
	 @PostMapping("/users/{userId}/coins")
	    public Coins createCoins(@PathVariable (value = "userId") Long userId,
	                                 @Valid @RequestBody Coins coins) throws ResourceNotFoundException {
	        return userrepository.findById(userId).map(user -> {
	            coins.setUser(user);
	            return coinRepository.save(coins);
	        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
	    }
	    
		// Update a coin
		@PutMapping("/users/{user_id}/coins")
		public Coins updateCoin(@PathVariable(value = "user_id") Long user_id,
		                                        @Valid @RequestBody Coins coins) throws ResourceNotFoundException {

		   User c = userrepository.findById(user_id)
		            .orElseThrow(() -> new ResourceNotFoundException("User"+ user_id+"not found"));

		   Coins cn=new Coins();	   
		    cn.setNo_of_coins(coins.getNo_of_coins());
		  //  cn.setRank_id(coins.getRank_id());
		    cn.setUser(c);
		    Coins updatedCoin = coinRepository.save(cn);
		    return updatedCoin;
		}
		
		// Get a Single user's coin details

	    @GetMapping("/users/{user_id}/coins")
	    public Coins getUserCoinById(@PathVariable(value = "user_id") Long user_id) throws ResourceNotFoundException {
	        return coinRepository.findById(user_id)
	                .orElseThrow(() -> new ResourceNotFoundException("user"+ user_id+"	not found"));
	    }
	    
	    
	    @GetMapping("/users/coins")
	    public List<Coins> getUserCoins()
	    {
	    	return coinRepository.findAll();
	    }
	    @PutMapping("/users/{userId}/coins/{CoinsId}")
	    public Coins updateCoins(@PathVariable(value = "userId") Long userId,
	        @PathVariable(value = "CoinsId") Long CoinsId, @Valid @RequestBody Coins coinsRequest)
	    throws ResourceNotFoundException {
	        if (!userrepository.existsById(userId)) {
	            throw new ResourceNotFoundException("userId not found");
	        }

	        return coinRepository.findById(CoinsId).map(coins-> {
	            coins.setNo_of_coins(coinsRequest.getNo_of_coins());
	            return coinRepository.save(coins);
	        }).orElseThrow(()-> new ResourceNotFoundException("coins id not found"));
	    }
	    
	    
	    

}
