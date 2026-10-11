package com.hirechart.hiredin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hirechart.hiredin.Dto.EducationDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Education;
import com.hirechart.hiredin.service.EducationService;

@RestController
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping("/education/create")
    public ResponseStructure<Education> createEducation(
            @RequestBody EducationDto educationDto) {

        return educationService.createEducation(educationDto);
    }
}