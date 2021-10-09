package uz.pdp.appweek7jpadb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek7jpadb.entity.Faculty;
import uz.pdp.appweek7jpadb.entity.University;
import uz.pdp.appweek7jpadb.payload.FacultyDTO;
import uz.pdp.appweek7jpadb.repository.FacultyRepository;
import uz.pdp.appweek7jpadb.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @GetMapping
    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }


    @PostMapping
    public String addFaculty(@RequestBody FacultyDTO facultyDTO){
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDTO.getName(), facultyDTO.getUniversityId());
        if (exists)
            return "This faculty in the uni already exist";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        Optional<University> universityOptional = universityRepository.findById(facultyDTO.getUniversityId());

        if(universityOptional.isEmpty()){
            return "University not found";
        }

        faculty.setUniversity(universityOptional.get());


        facultyRepository.save(faculty);

        return "Faculty added";

    }

    @GetMapping("/byUniversity/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id){
        try{
            facultyRepository.deleteById(id);
        }catch (Exception e){
            return "Error in deleting";
        }
        return "Faculty deleted";
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO ){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDTO.getName());
            Optional<University> universityOptional = universityRepository.findById(facultyDTO.getUniversityId());
            if (universityOptional.isEmpty()){
                return "University not found";
            }
            faculty.setUniversity(universityOptional.get());
            facultyRepository.save(faculty);
            return "Edited Faculty";
        }
        return "Not edited";
    }
}
