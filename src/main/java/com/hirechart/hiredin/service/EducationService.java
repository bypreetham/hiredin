package com.hirechart.hiredin.service;

import org.springframework.stereotype.Service;
import com.hirechart.hiredin.Dto.EducationDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Education;
import com.hirechart.hiredin.repository.EducationRepository;

@Service
public class EducationService {
    private EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }
    
    public ResponseStructure<Education> createEducation(EducationDto educationDto) {
        Education education = new Education();

        education.setDegree(educationDto.getDegree());
        education.setFieldOfStudy(educationDto.getFieldOfStudy());

        Education savedEducation = educationRepository.save(education);
        ResponseStructure<Education> response = new ResponseStructure<>();

        response.setStatuscode(201);
        response.setMessage("Education created successfully");
        response.setData(savedEducation);
        
        return response;
    }
}