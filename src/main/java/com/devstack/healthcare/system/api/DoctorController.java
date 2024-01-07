package com.devstack.healthcare.system.api;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.service.DoctorService;
import com.devstack.healthcare.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> createDoctor(@RequestBody RequestDoctorDto doctorDto){
        doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Doctor was saved",doctorDto.getName()),
                HttpStatus.CREATED
        );
        return doctorDto.getName();
    }

    @GetMapping("/{id}")
    public String findDoctor(@PathVariable String id){
        return id+"";
    }

    @PutMapping(params = "id")     //update through requesting ID
    public String updateDoctor(
            @RequestParam String id,
            @RequestBody RequestDoctorDto doctorDto){

        return doctorDto.toString();
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable String id){
        return id+"";
    }

    @GetMapping(path = "/list", params = {"searchText","page","size"})
    public String findAllDoctor(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return "FindAllDoctor";
    }
}
