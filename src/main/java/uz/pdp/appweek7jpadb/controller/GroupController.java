package uz.pdp.appweek7jpadb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek7jpadb.entity.Faculty;
import uz.pdp.appweek7jpadb.entity.Group;
import uz.pdp.appweek7jpadb.payload.GroupDTO;
import uz.pdp.appweek7jpadb.repository.FacultyRepository;
import uz.pdp.appweek7jpadb.repository.GroupRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;
 //All groups in table
    @GetMapping
    public List<Group> getGroups(){
       return groupRepository.findAll();
    }

    //All groups in University
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId){
//        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(universityId);
//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return groupsByUniversityIdNative;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDTO groupDTO){
        Group group = new Group();
        group.setName(groupDTO.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFacultyId());
        if (optionalFaculty.isEmpty())
            return "No such faculty";
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);

        return "Group added";
    }
}
