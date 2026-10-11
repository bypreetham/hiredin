package com.hirechart.hiredin.Controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hirechart.hiredin.Dto.AchievementDto;
import com.hirechart.hiredin.Dto.ApplicationResponseDto;
import com.hirechart.hiredin.Dto.CandidateAppliedJobDto;
import com.hirechart.hiredin.Dto.CandidateDto;
import com.hirechart.hiredin.Dto.CertificateDto;
import com.hirechart.hiredin.Dto.EducationDto;
import com.hirechart.hiredin.Dto.ExperienceDto;
import com.hirechart.hiredin.Dto.ProjectDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.Dto.SkillDto;
import com.hirechart.hiredin.entity.Achievements;
import com.hirechart.hiredin.entity.Candidate;
import com.hirechart.hiredin.entity.Certificate;
import com.hirechart.hiredin.entity.Education;
import com.hirechart.hiredin.entity.Experience;
import com.hirechart.hiredin.entity.Project;
import com.hirechart.hiredin.entity.Skill;
import com.hirechart.hiredin.service.ApplicationService;
import com.hirechart.hiredin.service.CandidateService;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ApplicationService applicationService;

    
    @PostMapping("/candidate/createaccount")
    public ResponseStructure<Candidate> createCandidateAcc(@Valid @RequestBody CandidateDto candidateDto) {
        return candidateService.createCandidate(candidateDto);
    }
    
    @GetMapping("/candidate/{candidateId}")
    public ResponseStructure<Candidate> findCandidate(@PathVariable Long candidateId) {
        return candidateService.findCandidate(candidateId);
    }

    @DeleteMapping("/candidate/{candidateId}")
    public ResponseStructure<String> deleteCandidate(@PathVariable Long candidateId) {
        return candidateService.deleteCandidate(candidateId);
    }

    @PutMapping({"/candidate/update/{candidateId}", "/candidate/{candidateId}"})
    public ResponseStructure<Candidate> updateCandidate(
            @PathVariable Long candidateId,
            @RequestBody CandidateDto candidateDto) {
        return candidateService.updateCandidate(candidateId, candidateDto);
    }


    @PostMapping("/candidate/education")
    public ResponseStructure<Education> addEducation(
            @RequestParam Long candidateId,
            @RequestBody EducationDto educationDto) {
        return candidateService.addEducation(candidateId, educationDto);
    }

    @DeleteMapping("/candidate/education/{educationId}")
    public ResponseStructure<String> deleteEducation(
            @RequestParam Long candidateId,
            @PathVariable Long educationId) {
        return candidateService.deleteEducation(candidateId, educationId);
    }

    @PostMapping("/candidate/experience")
    public ResponseStructure<Experience> addExperience(
            @RequestParam Long candidateId,
            @RequestBody ExperienceDto experienceDto) {
        return candidateService.addExperience(candidateId, experienceDto);
    }

    @DeleteMapping("/candidate/experience/{experienceId}")
    public ResponseStructure<String> deleteExperience(
            @RequestParam Long candidateId,
            @PathVariable Long experienceId) {
        return candidateService.deleteExperience(candidateId, experienceId);
    }

    @PostMapping("/candidate/skill")
    public ResponseStructure<Skill> addSkill(
            @RequestParam Long candidateId,
            @RequestBody SkillDto skillDto) {
        return candidateService.addSkill(candidateId, skillDto);
    }

    @DeleteMapping("/candidate/skill/{skillId}")
    public ResponseStructure<String> deleteSkill(
            @RequestParam Long candidateId,
            @PathVariable Long skillId) {
        return candidateService.deleteSkill(candidateId, skillId);
    }

    @GetMapping("/candidate/{candidateId}/skills")
    public ResponseStructure<List<Skill>> getSkills(@PathVariable Long candidateId) {
        return candidateService.getSkills(candidateId);
    }

    @PutMapping("/candidate/{candidateId}/skills")
    public ResponseStructure<List<Skill>> updateSkillsCascade(
            @PathVariable Long candidateId,
            @RequestBody List<SkillDto> skillDtos) {
        return candidateService.updateSkillsCascade(candidateId, skillDtos);
    }

  
    @PostMapping("/candidate/achievement")
    public ResponseStructure<Achievements> addAchievement(
            @RequestParam Long candidateId,
            @RequestBody AchievementDto achievementDto) {
        return candidateService.addAchievement(candidateId, achievementDto);
    }

    @DeleteMapping("/candidate/achievement/{achievementId}")
    public ResponseStructure<String> deleteAchievement(
            @RequestParam Long candidateId,
            @PathVariable Long achievementId) {
        return candidateService.deleteAchievement(candidateId, achievementId);
    }

    @PostMapping("/candidate/project")
    public ResponseStructure<Project> addProject(
            @RequestParam Long candidateId,
            @RequestBody ProjectDto projectDto) {
        return candidateService.addProject(candidateId, projectDto);
    }

    @DeleteMapping("/candidate/project/{projectId}")
    public ResponseStructure<String> deleteProject(
            @RequestParam Long candidateId,
            @PathVariable Long projectId) {
        return candidateService.deleteProject(candidateId, projectId);
    }

    @PostMapping("/candidate/certificate")
    public ResponseStructure<Certificate> addCertificate(
            @RequestParam Long candidateId,
            @RequestBody CertificateDto certificateDto) {
        return candidateService.addCertificate(candidateId, certificateDto);
    }

    @DeleteMapping("/candidate/certificate/{certificateId}")
    public ResponseStructure<String> deleteCertificate(
            @RequestParam Long candidateId,
            @PathVariable Long certificateId) {
        return candidateService.deleteCertificate(candidateId, certificateId);
    }

    @PostMapping("/candidate/apply")
    public ResponseStructure<ApplicationResponseDto> applyJob(
            @RequestParam Long candidateId,
            @RequestParam Long jobId) {
        return applicationService.applyJob(candidateId, jobId);
    }

    @DeleteMapping("/candidate/application/{applicationId}")
    public ResponseStructure<String> revokeApplication(
            @RequestParam Long candidateId,
            @PathVariable Long applicationId) {
        return applicationService.revokeApplication(candidateId, applicationId);
    }

    @GetMapping("/candidate/{candidateId}/applications")
    public ResponseStructure<List<CandidateAppliedJobDto>> getAppliedJobs(@PathVariable Long candidateId) {
        return applicationService.getCandidateApplications(candidateId);
    }

    @GetMapping("/candidate/{candidateId}/applications/shortlisted")
    public ResponseStructure<List<CandidateAppliedJobDto>> getShortlistedApplications(@PathVariable Long candidateId) {
        return applicationService.getCandidateShortlistedApplications(candidateId);
    }

    @GetMapping("/candidate/{candidateId}/applications/rejected")
    public ResponseStructure<List<CandidateAppliedJobDto>> getRejectedApplications(@PathVariable Long candidateId) {
        return applicationService.getCandidateRejectedApplications(candidateId);
    }
}