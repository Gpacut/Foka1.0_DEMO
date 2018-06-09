package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.model.Work;
import pl.edu.agh.ki.mwo.model.Evaluation;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;

@Controller
public class WorksController {

    @RequestMapping(value="/Works")
    public String listWorks(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

    	model.addAttribute("works", DatabaseConnector.getInstance().getWorks());
    	
        return "worksList";    
    }
    
    @RequestMapping(value="/AddWork")
    public String displayAddWorkForm(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
        return "workForm";    
    }

    @RequestMapping(value="/CreateWork", method=RequestMethod.POST)
    public String createWork(
    		@RequestParam(value="workTitle", required=false) String title,
    		@RequestParam(value="workAuthor", required=false) String author,
    		@RequestParam(value="workKeyWords", required=false) String keyWords,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Work work = new Work();
    	work.setTitle(title);
    	work.setAuthor(author);
    	work.setKeyWords(keyWords);
    	
    	DatabaseConnector.getInstance().addWork(work);    	
       	model.addAttribute("works", DatabaseConnector.getInstance().getWorks());
    	model.addAttribute("message", "Nowa praca została dodana");
         	
    	return "worksList";
    }
    @RequestMapping(value="/AddToEvaluation", method=RequestMethod.POST)
    public String AddToEvaluation(
    		@RequestParam(value="workToEvaluationTitle", required=false) String title,
    		@RequestParam(value="workToEvaluationAuthor", required=false) String author,
    		@RequestParam(value="workToEvaluationKeyWords", required=false) String keyWords,
    		@RequestParam(value="workToEvaluationGrade", required=false) String grade,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Evaluation workToEvaluation = new Evaluation();
    	workToEvaluation.setTitle(title);
    	workToEvaluation.setAuthor(author);
    	workToEvaluation.setKeyWords(keyWords);
    	workToEvaluation.setGrade(grade);
    	
    	DatabaseConnector.getInstance().addToEvaluation(workToEvaluation);    	
       	model.addAttribute("worksToEvaluation", DatabaseConnector.getInstance().getWorksToEval());
    	model.addAttribute("message", "Praca została dodana do oceny");
         	
    	return "evaluationsList";
    }
    
    @RequestMapping(value="/DeleteWork", method=RequestMethod.POST)
    public String deleteWork(@RequestParam(value="workId", required=false) String workId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	DatabaseConnector.getInstance().deleteWork(workId);    	
       	model.addAttribute("works", DatabaseConnector.getInstance().getWorks());
    	model.addAttribute("message", "Praca została usunięta");
         	
    	return "worksList";
    }
    
    @RequestMapping(value="/Edit")
    public String displayEditForm(
    		@RequestParam(value="workId", required=false) long workId,
    		@RequestParam(value="workTitle", required=false) String editTitle,
    		@RequestParam(value="workAuthor", required=false) String editAuthor,
    		@RequestParam(value="workKeyWords", required=false) String editKeyWords,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	model.addAttribute("workId", workId);
    	model.addAttribute("title", editTitle);
    	model.addAttribute("author", editAuthor);
    	model.addAttribute("keyWords", editKeyWords);
    	
        return "editForm";    
    }
    
    @RequestMapping(value="/EditWork", method=RequestMethod.POST)
    public String editWork(
    		@RequestParam(value="workId", required=false) long workId,
    		@RequestParam(value="workTitle", required=false) String title,
    		@RequestParam(value="workAuthor", required=false) String author,
    		@RequestParam(value="workKeyWords", required=false) String keyWords,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Work work = new Work();
    	work.setId(workId);
    	work.setTitle(title);
    	work.setAuthor(author);
    	work.setKeyWords(keyWords);
    	
    	DatabaseConnector.getInstance().editWork(work);    	
       	model.addAttribute("works", DatabaseConnector.getInstance().getWorks());
    	model.addAttribute("message", "Nowa praca została dodana");
         	
    	return "worksList";
    }



}