package br.project.account.controller;

import br.project.account.dao.PersonDao;
import br.project.account.model.AddressType;
import br.project.account.model.Person;
import br.project.account.model.PersonType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Project Account
 * @author Ronaldo Torre
 */
@Controller("personController")
@RequestMapping("/person")
public class PersonController {
    
	private final PersonDao personDao;
    
    private List<Person> LstPerson;

    public PersonController() {
        this.personDao = new PersonDao();
        this.LstPerson = new ArrayList<>();
    }
    
    @RequestMapping(value="/" , method = RequestMethod.GET)
    public String listPerson(Model model){
        LstPerson = personDao.getAll();
        model.addAttribute("people", LstPerson);
        return "/person/index";
    }
    
    @RequestMapping(value= "/new", method = RequestMethod.GET)
    public String createPerson(Model model){
    	model.addAttribute("person", new Person());
    	return "/person/create";
    }
    
    @RequestMapping(value= "/edit/{id}", method = RequestMethod.GET)
    public String editPerson(Model model,@PathVariable("id") int id){
    	Person person = this.personDao.getById(id);
    	model.addAttribute(person);
    	return "/person/edit";
    }
    
    @RequestMapping(value= "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePerson(@PathVariable("id") int id){
    	Person person = this.personDao.getById(id);
    	this.personDao.delete(person);
    	return new ModelAndView("redirect:/person/");
    }
    
    @RequestMapping(value= "/view/{id}", method = RequestMethod.GET)
    public String viewPerson(Model model,@PathVariable("id") int id){
    	Person person = this.personDao.getById(id);
    	model.addAttribute("person", person);
        return "/person/detail";
    }
    
    @RequestMapping(value= "/find", method = RequestMethod.GET)
    public String findPerson(){
    	return "/person/find";
    }
    
    @RequestMapping(value= "/find", method = RequestMethod.POST)
    public String findPersonParams(Model model,
    							   @RequestParam("typeId") String type,
    							   @RequestParam("txtFind") String value
    							  ){
    	
    	try{
    		if((!type.isEmpty() && type!= null)&&(!value.isEmpty() && value != null)){
    			String[] params = new String[1];
    			String[] values = new String[1];
    			
    			if(type.equals("1") || type == "1"){
    				params[0]= "p.Name";
    				values[0]= value;
        		}
        		else if(type.equals("2") || type == "2"){
        			params[0]= "p.Document1";
        			values[0]= value;
        		}
        		
    			LstPerson = personDao.getAllByParamters(params, values);
        		model.addAttribute("people", LstPerson);
        	}
    	}
    	catch (Exception e) {
    		throw new IllegalArgumentException("Error while search person! " + e.getMessage());
		}
    	
    	return "/person/index";
    }
    
    @RequestMapping(value= "/save", method = RequestMethod.POST)
    public ModelAndView savePerson(@ModelAttribute("person") Person person, BindingResult result ){
         
        if(result.hasErrors()){
            //return "personform";
        }
        
        if(person.getId()== null){
        	
        	if(person.getTypeId() == 1 || person.getType().getCode() == 1){
        		if(person.getGenderId() == 1){
        			person.setGenderId(1);
                	person.setGender(PersonType.Gender.female);
                }
                else if(person.getGenderId() == 2){
                	person.setGenderId(2);
                	person.setGender(PersonType.Gender.male);
                }
        	}
        	
            if(person.getAddress().getTypeId() != null){
                person.getAddress().setTypeById(person.getAddress().getTypeId());
            }
            
            person.setAddDate(new Date());
            person.setAddUser("@System");
            person.getAddress().setAddDate(person.getAddDate());
            person.getAddress().setAddUser(person.getAddUser());
            
            personDao.insert(person);
        }
        else {
            
            if(person.getTypeId() == 1){
            	person.setType(PersonType.Type.physical);
            	
        		if(person.getGenderId() == 1){
        			person.setGenderId(1);
                	person.setGender(PersonType.Gender.female);
                }
                else if(person.getGenderId() == 2){
                	person.setGenderId(2);
                	person.setGender(PersonType.Gender.male);
                }
        	}
            else {
            	person.setType(PersonType.Type.juridical);
            	
            }
            
            if(person.getAddress().getTypeId() != null){
                person.getAddress().setTypeId(person.getAddress().getTypeId());
                
                if(person.getAddress().getTypeId() == 0)
                	person.getAddress().setType(AddressType.Type.comercial);
                else if(person.getAddress().getTypeId() == 1)
                	person.getAddress().setType(AddressType.Type.delivery);
                else if(person.getAddress().getTypeId() == 2)
                	person.getAddress().setType(AddressType.Type.residential);
                else if(person.getAddress().getTypeId() == 3)
                	person.getAddress().setType(AddressType.Type.others);
                
            }
            
            person.setUpdateDate(new Date());
            person.setUpdateUser("@Adm");
            person.getAddress().setUpdateDate(person.getUpdateDate());
            person.getAddress().setUpdateUser(person.getUpdateUser());
            
            personDao.update(person);
        }
        
        return new ModelAndView("redirect:/person/");
    }
    
}