package com.devstack.healthcare.system.service.impl;

import com.devstack.healthcare.system.dto.request.RequestDoctorDto;
import com.devstack.healthcare.system.dto.response.ResponseDoctorDto;
import com.devstack.healthcare.system.dto.response.paginated.PaginatedDoctorResponseDto;
import com.devstack.healthcare.system.entity.Doctor;
import com.devstack.healthcare.system.exception.EntryNotFoundException;
import com.devstack.healthcare.system.repo.DoctorRepo;
import com.devstack.healthcare.system.service.DoctorService;
import com.devstack.healthcare.system.util.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;

    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }

    UUID uuid = UUID.randomUUID();
    long docId = uuid.getMostSignificantBits();
    @Override
    public void createDoctor(RequestDoctorDto dto) {

        Doctor doctor = new Doctor(
          docId, dto.getName(), dto.getAddress(),dto.getContact(),dto.getSalary()
        );

        doctorRepo.save(doctor);
    }

    @Override
    public ResponseDoctorDto getDoctor(long id) {    /////exception handling
       Optional<Doctor> selectDoctor =doctorRepo.findById(id);
       if (selectDoctor.isEmpty()){
           throw new EntryNotFoundException("Doctor Is Not Found");
       }
       return doctorMapper.toResponseDoctorDto(selectDoctor.get());
    }

    @Override
    public void deleteDoctor(long id) {

        Optional<Doctor> selectDoctor =doctorRepo.findById(id);
        if (selectDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor Is Not Found");
        }
        doctorRepo.deleteById(selectDoctor.get().getId());

    }


    /////return all doctors according to the name
    @Override
    public List<ResponseDoctorDto> findDoctorByName(String name) {
        List<Doctor> allByName =  doctorRepo.findAllByName(name);
        return  null;
    }

    @Override
    public void updateDoctor(long id, RequestDoctorDto dto) {

        Optional<Doctor> selectDoctor =doctorRepo.findById(id);
        if (selectDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor Is Not Found");
        }
        Doctor doc = selectDoctor.get();
        doc.setName(dto.getName());
        doc.setAddress(dto.getAddress());
        doc.setContact(dto.getContact());
        doc.setSalary(dto.getSalary());
        doctorRepo.save(doc);

    }

    @Override
    public PaginatedDoctorResponseDto getAllDoctors(String searchText, int page, int size) {
        searchText ="%"+searchText+"%";
        List<Doctor> doctors = doctorRepo.searchDoctors(searchText, PageRequest.of(page,size));
        long doctorCount = doctorRepo.countDoctors(searchText);
        List<ResponseDoctorDto> dtos = doctorMapper.toResponseDoctorDtoList(doctors);
        /*doctors.forEach(doc->{
            dtos.add(
                    new ResponseDoctorDto(
                            doc.getId(), doc.getName(), doc.getAddress(), doc.getContact(), doc.getSalary()
                    )
            );
        });*/
        return new PaginatedDoctorResponseDto(
                doctorCount,
                dtos
        );
    }
}
