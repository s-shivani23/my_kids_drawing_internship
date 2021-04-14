package mykiddrawing.quiz.Controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import mykiddrawing.quiz.model.*;
import mykiddrawing.quiz.repository.*;
import mykiddrawing.quiz.exception.*;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;


@RestController
@RequestMapping("/question")
public class QuestionsController {
	
	@Value("${questionImage.upload-dir}")
	private String uploadPath; 
	 @Autowired
	 	private QuestionRepository questionrepo;
	 @Autowired
	 	private AdminRepository adminrepo;
	
	@PostMapping("/create/{userId}")
	public Questionsmodel storeFile(@PathVariable Long userId,@RequestParam("file") MultipartFile file,@ModelAttribute Questionsmodel question,HttpServletRequest request) throws FileStorageException, IOException, UnauthorisedException 
	{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
	    String dbApiToken = adminrepo.getApiToken(userId);
	    
	    String headerToken = request.getHeader("apiToken");
	    System.out.println(dbApiToken);
	    System.out.println(headerToken
	    		);
	  if(!dbApiToken.equals(headerToken))
	  {
		  String error = "UnAuthorised User";
	    	
			String message = "Not Successful";
		
			throw new UnauthorisedException(401, error, message);
	  }
	  try {
	           
		       // Check if the file's name contains invalid characters
	           if(fileName.contains("..") || fileName.isEmpty()) { 
	        	   throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);	
	               
	           }
		}
		catch(FileStorageException ex)
		{
			  throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
		}
		long unique = new Date().getTime();
		String tumbnailName = unique + "-thumbnail-" + file.getOriginalFilename().replace(" ", "_"); 
		OutputStream opStream = null;
		question.setFilename(fileName);
	    question.setTumbnailName(tumbnailName);
		Path path=Paths.get(uploadPath).toAbsolutePath().normalize();
	    try {
			byte[] byteContent = file.getBytes();
			Files.createDirectories(path);
			File myFile = new File(uploadPath + fileName);
			// destination path
			System.out.println("fileName is " + myFile);
			// check if file exist, otherwise create the file before writing
			if (!myFile.exists()) {
				myFile.createNewFile();
			}
			opStream = new FileOutputStream(myFile);
			opStream.write(byteContent);
			opStream.flush();
			opStream.close();
		} 
	    catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File destinationDir = new File(uploadPath);
			Thumbnails.of(uploadPath + fileName).size(900, 800).toFiles(destinationDir, Rename.NO_CHANGE);
			Thumbnails.of(new File(uploadPath + fileName)).size(348, 235).toFile(uploadPath + tumbnailName);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return questionrepo.save(question);
	}
	@GetMapping("/getQuestion/{topic}")
	public List<String> getQuestion(@PathVariable String topic) throws Exception
	{
		List<String> questions = questionrepo.getQuestionByTopic(topic);
		if(questions.isEmpty())
			throw new Exception("SubCategory is not avilable for You");
		return questions;		
	}
	@GetMapping("/getImage/{question}")
	public File getImage(@PathVariable String question) throws IOException
	{
			  String filename=questionrepo.getImage(question);
			  String destinationDir = uploadPath + filename;
			  System.out.println(filename);
			  File image = ResourceUtils.getFile(destinationDir);
			  return image;
	}
	@PutMapping("/updateQuestion/{question}")
	public Questionsmodel updateQuestion(@RequestParam MultipartFile file,@PathVariable String question,String updatedQuestion) throws FileStorageException, ResourceNotFoundException
	{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
	           
		       // Check if the file's name contains invalid characters
	           if(fileName.contains("..") || fileName.isEmpty()) { 
	        	   throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);	
	               
	           }
	    }
		catch(FileStorageException ex)
		{
			  throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
		}
		long unique = new Date().getTime();
		String tumbnailName = unique + "-thumbnail-" + file.getOriginalFilename().replace(" ", "_"); 
		OutputStream opStream = null;
		int id=questionrepo.getId(question);
		Questionsmodel selectedQuestion = questionrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quwstion not found :: " + id));
		selectedQuestion.setFilename(fileName);
		selectedQuestion.setTumbnailName(tumbnailName);
		Path path=Paths.get(uploadPath).toAbsolutePath().normalize();
	    try {
			byte[] byteContent = file.getBytes();
			Files.createDirectories(path);
			File myFile = new File(uploadPath + fileName);
			// destination path
			System.out.println("fileName is " + myFile);
			// check if file exist, otherwise create the file before writing
			if (!myFile.exists()) {
				myFile.createNewFile();
			}
			opStream = new FileOutputStream(myFile);
			opStream.write(byteContent);
			opStream.flush();
			opStream.close();
		} 
	    catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File destinationDir = new File(uploadPath);
			Thumbnails.of(uploadPath + fileName).size(900, 800).toFiles(destinationDir, Rename.NO_CHANGE);
			Thumbnails.of(new File(uploadPath + fileName)).size(348, 235).toFile(uploadPath + tumbnailName);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedQuestion.setQuestion(updatedQuestion);
		Questionsmodel updatedquestion=questionrepo.save(selectedQuestion);
		return updatedquestion;
	}
	
	
	
//Options Part
	@Autowired OptionsRepo optionsrep;
	 @PostMapping("/createoptions/{id}")
	    public  Options createoptions(@Valid @RequestBody Options option,@PathVariable int id) throws ResourceNotFoundException{
		 return questionrepo.findById(id).map(question -> {
			 option.setQuestion(question);
			 return optionsrep.save(option);
		 }).orElseThrow(()-> new ResourceNotFoundException("Quwstion not found :: " + id));
	 
	 }
	 @GetMapping("/getOptions/{id}")
	
	 public List<String> getbyquestionid(@PathVariable(value = "id") int id) throws Exception
		{
			return 	optionsrep.getbyquestionid(id);	
		}
}