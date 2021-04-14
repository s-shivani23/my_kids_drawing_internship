package mykiddrawing.quiz.Controller;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mykiddrawing.quiz.exception.ResourceNotFoundException;
import mykiddrawing.quiz.model.Result;
import mykiddrawing.quiz.repository.ResultRepository;
import mykiddrawing.quiz.repository.UserRepository;

@CrossOrigin(origins="*")
@RestController

@RequestMapping("/api/v1")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private  UserRepository userRepository;

    @GetMapping("/users/{userId}/results")
    public List < Result > getResultsByUser(@PathVariable(value = "userId") Long userId) {
        return resultRepository.findByUserId(userId);
    }
    @GetMapping("/leadboard")
    public List<String> getbyname() {
    	List<String> Result=resultRepository.getbyname();
    	return Result;
    }
    
    @GetMapping("/getQuestion/{userId}")
	public List<String> getId(@PathVariable(value = "userId") Long userId) throws Exception
	{
		List<String> Result = resultRepository.getId(userId);
		if(Result.isEmpty())
			throw new Exception("No Result");
		return Result;		
	}
    @PostMapping("/users/{userId}/results")
    public Result createResult(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Result result) throws ResourceNotFoundException {
        return userRepository.findById(userId).map(user -> {
            result.setUser(user);
            return resultRepository.save(result);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }
    

    @PutMapping("/users/{userId}/results/{ResultId}")
    public Result updateResult(@PathVariable(value = "userId") Long userId,
        @PathVariable(value = "ResultId") Long ResultId, @Valid @RequestBody Result resultRequest)
    throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId not found");
        }

        return resultRepository.findById(ResultId).map(result-> {
            result.setTopic(resultRequest.getTopic());
            result.setScore(resultRequest.getScore());
            result.setCoin(resultRequest.getCoin());
            return resultRepository.save(result);
        }).orElseThrow(()-> new ResourceNotFoundException("result id not found"));
    }
   
    
}