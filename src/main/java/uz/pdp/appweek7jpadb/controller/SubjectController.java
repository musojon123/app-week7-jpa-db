package uz.pdp.appweek7jpadb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek7jpadb.entity.Subject;
import uz.pdp.appweek7jpadb.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
       if ( subjectRepository.existsByName(subject.getName()) )
           return "This subject already exist";
        subjectRepository.save(subject);
        return "Subject added";
    }

    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }



}
