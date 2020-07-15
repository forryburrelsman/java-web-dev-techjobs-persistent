package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

//    @GetMapping("add")
//    public String displayAddJobForm(Model model) {
////        Optional<Job> result = jobRepository.findById(jobId);
////        Job job = result.get();
//        model.addAttribute("title", "Add Job");
//        model.addAttribute(new Job());
//        JobSkillDTO jobSkill = new JobSkillDTO();
//        jobSkill.setJob(job);
//        model.addAttribute("employers", employerRepository.findAll());
//        model.addAttribute("skills", skillRepository.findAll());
//        model.addAttribute("jobSkill", jobSkill);
//        return "add";
//    }

//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        }
//
//        jobRepository.save(newJob);
//        model.addAttribute("jobRepository", jobRepository);
//        model.addAttribute("employerRepository", employerRepository);
//        return "redirect:";
//        //return "add";
//    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        Optional<Employer> employer = employerRepository.findById(employerId);
        Employer thisEmployer = employer.get();
        newJob.setEmployer(thisEmployer);
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);
//            model.addAttribute("jobRepository", jobRepository);
//            model.addAttribute("employerRepository", employerRepository);
        return "view";
        //return "add";
    }

    @GetMapping("view")
    public String displayAllJobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "view";
    }


    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "view";
        }

    }
}
