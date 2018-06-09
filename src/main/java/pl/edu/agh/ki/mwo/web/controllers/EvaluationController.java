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
public class EvaluationController {

    @RequestMapping(value="/WorksToEval")
    public String listEvaluation(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

    	model.addAttribute("worksToEvaluation", DatabaseConnector.getInstance().getWorksToEval());
    	
        return "evaluationsList";    
    }
    
    @RequestMapping(value="/DeleteWorkToEvaluation", method=RequestMethod.POST)
    public String deleteWorkToEvaluation(@RequestParam(value="workToEvaluationId", required=false) String workToEvaluationId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	DatabaseConnector.getInstance().deleteWorkToEvaluation(workToEvaluationId);    	
       	model.addAttribute("worksToEvaluation", DatabaseConnector.getInstance().getWorksToEval());
    	model.addAttribute("message", "Praca została usunięta");
         	
    	return "evaluationsList";
    }
    
    @RequestMapping(value="/Evaluate")
    public String displayEditForm(
    		@RequestParam(value="workToEvaluationId", required=false) long workToEvaluationId,
    		@RequestParam(value="workToEvaluationTitle", required=false) String workToEvaluationTitle,
    		@RequestParam(value="workToEvaluationAuthor", required=false) String workToEvaluationAuthor,
    		@RequestParam(value="workToEvaluationKeyWords", required=false) String workToEvaluationKeyWords,
    		@RequestParam(value="workToEvaluationGrade", required=false) String workToEvaluationGrade,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	model.addAttribute("workToEvaluationId", workToEvaluationId);
    	model.addAttribute("workToEvaluationTitle", workToEvaluationTitle);
    	model.addAttribute("workToEvaluationAuthor", workToEvaluationAuthor);
    	model.addAttribute("workToEvaluationKeyWords", workToEvaluationKeyWords);
    	model.addAttribute("workToEvaluationGrade", workToEvaluationGrade);
    	
        return "evaluateForm";    
    }
    
    @RequestMapping(value="/EditGrade", method=RequestMethod.POST)
    public String editWork(
    		@RequestParam(value="workToEvaluationId", required=false) long workToEvaluationId,
    		@RequestParam(value="workToEvaluationTitle", required=false) String workToEvaluationTitle,
    		@RequestParam(value="workToEvaluationAuthor", required=false) String workToEvaluationAuthor,
    		@RequestParam(value="workToEvaluationKeyWords", required=false) String workToEvaluationKeyWords,
    		@RequestParam(value="workToEvaluationGrade", required=false) String workToEvaluationGrade,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Evaluation workToEvaluation = new Evaluation();
    	workToEvaluation.setId(workToEvaluationId);
    	workToEvaluation.setTitle(workToEvaluationTitle);
    	workToEvaluation.setAuthor(workToEvaluationAuthor);
    	workToEvaluation.setKeyWords(workToEvaluationKeyWords);
    	workToEvaluation.setGrade(workToEvaluationGrade);
    	
    	DatabaseConnector.getInstance().editGrade(workToEvaluation);    	
       	model.addAttribute("worksToEvaluation", DatabaseConnector.getInstance().getWorksToEval());
    	model.addAttribute("message", "Ocena została dodana");
         	
    	return "evaluationsList";
    }


}