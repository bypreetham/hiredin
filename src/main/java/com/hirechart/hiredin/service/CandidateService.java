package com.hirechart.hiredin.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.hirechart.hiredin.Dto.AchievementDto;
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
import com.hirechart.hiredin.entity.PersonalInfo;
import com.hirechart.hiredin.entity.Project;
import com.hirechart.hiredin.entity.Skill;
import com.hirechart.hiredin.exception.CandidateNotFound;
import com.hirechart.hiredin.exception.InvalidCandidateDataException;
import com.hirechart.hiredin.repository.AchievementRepository;
import com.hirechart.hiredin.repository.CandidateRepository;
import com.hirechart.hiredin.repository.CertificateRepository;
import com.hirechart.hiredin.repository.EducationRepository;
import com.hirechart.hiredin.repository.ExperienceRepository;
import com.hirechart.hiredin.repository.ProjectRepository;
import com.hirechart.hiredin.repository.SkillRepository;

@Service
public class CandidateService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern AGE_PATTERN = Pattern.compile("^(1[89]|[2-9][0-9]|1[0-4][0-9]|150)$");

    private final CandidateRepository candidateRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;
    private final AchievementRepository achievementRepository;
    private final ProjectRepository projectRepository;
    private final CertificateRepository certificateRepository;

    public CandidateService(
        CandidateRepository candidateRepository,
        EducationRepository educationRepository,
        ExperienceRepository experienceRepository,
        SkillRepository skillRepository,
        AchievementRepository achievementRepository,
        ProjectRepository projectRepository,
        CertificateRepository certificateRepository) {

        this.candidateRepository = candidateRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository;
        this.achievementRepository = achievementRepository;
        this.projectRepository = projectRepository;
        this.certificateRepository = certificateRepository;
    }

    public ResponseStructure<Candidate> createCandidate(CandidateDto candidateDto) {
        validateCandidateDto(candidateDto);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName(candidateDto.getName());
        personalInfo.setPhone(candidateDto.getPhone());
        personalInfo.setEmail(candidateDto.getEmail());
        personalInfo.setAge(candidateDto.getAge());

        Candidate candidate = new Candidate();
        candidate.setPersonalInfo(personalInfo);
        Candidate savedCandidate = candidateRepository.save(candidate);

        ResponseStructure<Candidate> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Candidate account created successfully");
        response.setData(savedCandidate);

        return response;
    }

    private void validateCandidateDto(CandidateDto candidateDto) {
        if (candidateDto.getEmail() == null || !EMAIL_PATTERN.matcher(candidateDto.getEmail()).matches()) {
            throw new InvalidCandidateDataException("Invalid email format. Email must contain '@' and a valid domain.");
        }
        if (candidateDto.getPhone() == null || !PHONE_PATTERN.matcher(candidateDto.getPhone()).matches()) {
            throw new InvalidCandidateDataException("Invalid phone number. Phone number must be exactly 10 digits.");
        }
        if (!AGE_PATTERN.matcher(String.valueOf(candidateDto.getAge())).matches()) {
            throw new InvalidCandidateDataException("Invalid age: " + candidateDto.getAge() + ". Age must be between 18 and 150.");
        }
    }

    public ResponseStructure<Candidate> findCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        ResponseStructure<Candidate> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Candidate found successfully");
        response.setData(candidate);
        return response;
    }

    public ResponseStructure<String> deleteCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        candidateRepository.delete(candidate);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Candidate deleted successfully");
        response.setData("Candidate " + candidateId + " deleted");

        return response;
    }

    public ResponseStructure<Candidate> updateCandidate(Long candidateId, CandidateDto candidateDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        PersonalInfo personalInfo = candidate.getPersonalInfo();
        if (personalInfo == null) {
            personalInfo = new PersonalInfo();
            candidate.setPersonalInfo(personalInfo);
        }

        if (candidateDto.getName() != null && !candidateDto.getName().isBlank()) {
            personalInfo.setName(candidateDto.getName());
        }
        if (candidateDto.getPhone() != null && !candidateDto.getPhone().isBlank()) {
            personalInfo.setPhone(candidateDto.getPhone());
        }
        if (candidateDto.getEmail() != null && !candidateDto.getEmail().isBlank()) {
            personalInfo.setEmail(candidateDto.getEmail());
        }
        if (candidateDto.getAge() > 0) {
            personalInfo.setAge(candidateDto.getAge());
        }

        Candidate updatedCandidate = candidateRepository.save(candidate);

        ResponseStructure<Candidate> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Candidate profile updated successfully");
        response.setData(updatedCandidate);
        return response;
    }

    // ================= EDUCATION =================
    public ResponseStructure<Education> addEducation(Long candidateId, EducationDto educationDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Education education = new Education();
        education.setDegree(educationDto.getDegree());
        education.setFieldOfStudy(educationDto.getFieldOfStudy());
        education.setCandidate(candidate);

        Education savedEducation = educationRepository.save(education);
        ResponseStructure<Education> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Education added successfully");
        response.setData(savedEducation);
        return response;
    }

    public ResponseStructure<String> deleteEducation(Long candidateId, Long educationId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education with id " + educationId + " not found"));

        if (education.getCandidate() == null || !education.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Education does not belong to candidate id: " + candidateId);
        }

        educationRepository.delete(education);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Education deleted successfully");
        response.setData("Education " + educationId + " deleted for candidate " + candidateId);
        return response;
    }

    // ================= EXPERIENCE =================
    public ResponseStructure<Experience> addExperience(Long candidateId, ExperienceDto experienceDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Experience experience = new Experience();
        experience.setCompanyName(experienceDto.getCompanyName());
        experience.setRole(experienceDto.getRole());
        experience.setYearsOfExperience(experienceDto.getYearsOfExperience());
        experience.setCandidate(candidate);

        Experience savedExperience = experienceRepository.save(experience);
        ResponseStructure<Experience> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Experience added successfully");
        response.setData(savedExperience);
        return response;
    }

    public ResponseStructure<String> deleteExperience(Long candidateId, Long experienceId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new RuntimeException("Experience with id " + experienceId + " not found"));

        if (experience.getCandidate() == null || !experience.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Experience does not belong to candidate id: " + candidateId);
        }

        experienceRepository.delete(experience);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Experience deleted successfully");
        response.setData("Experience " + experienceId + " deleted for candidate " + candidateId);
        return response;
    }

    // ================= SKILLS =================
    public ResponseStructure<Skill> addSkill(Long candidateId, SkillDto skillDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Skill skill = new Skill();
        skill.setCategory(skillDto.getCategory());
        skill.setSkills(skillDto.getSkills());
        skill.setCandidate(candidate);

        Skill savedSkill = skillRepository.save(skill);
        ResponseStructure<Skill> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Skill added successfully");
        response.setData(savedSkill);
        return response;
    }

    public ResponseStructure<String> deleteSkill(Long candidateId, Long skillId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill with id " + skillId + " not found"));

        if (skill.getCandidate() == null || !skill.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Skill does not belong to candidate id: " + candidateId);
        }

        skillRepository.delete(skill);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Skill deleted successfully");
        response.setData("Skill " + skillId + " deleted for candidate " + candidateId);
        return response;
    }

    public ResponseStructure<java.util.List<Skill>> getSkills(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        ResponseStructure<java.util.List<Skill>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Retrieved candidate skills successfully");
        response.setData(candidate.getSkills());
        return response;
    }

    public ResponseStructure<java.util.List<Skill>> updateSkillsCascade(Long candidateId, java.util.List<SkillDto> skillDtos) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        java.util.List<Skill> newSkills = new java.util.ArrayList<>();
        if (skillDtos != null) {
            for (SkillDto dto : skillDtos) {
                Skill skill = new Skill();
                skill.setCategory(dto.getCategory());
                skill.setSkills(dto.getSkills());
                skill.setCandidate(candidate);
                newSkills.add(skill);
            }
        }
        candidate.setSkills(newSkills);
        Candidate savedCandidate = candidateRepository.save(candidate);

        ResponseStructure<java.util.List<Skill>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Candidate skills updated successfully via cascade");
        response.setData(savedCandidate.getSkills());
        return response;
    }

    // ================= ACHIEVEMENTS =================
    public ResponseStructure<Achievements> addAchievement(Long candidateId, AchievementDto achievementDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Achievements achievement = new Achievements();
        achievement.setDescription(achievementDto.getDescription());
        achievement.setCandidate(candidate);

        Achievements savedAchievement = achievementRepository.save(achievement);
        ResponseStructure<Achievements> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Achievement added successfully");
        response.setData(savedAchievement);
        return response;
    }

    public ResponseStructure<String> deleteAchievement(Long candidateId, Long achievementId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Achievements achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new RuntimeException("Achievement with id " + achievementId + " not found"));

        if (achievement.getCandidate() == null || !achievement.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Achievement does not belong to candidate id: " + candidateId);
        }

        achievementRepository.delete(achievement);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Achievement deleted successfully");
        response.setData("Achievement " + achievementId + " deleted for candidate " + candidateId);
        return response;
    }

    // ================= PROJECTS =================
    public ResponseStructure<Project> addProject(Long candidateId, ProjectDto projectDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setTechnologiesUsed(projectDto.getTechnologiesUsed());
        project.setProjectUrl(projectDto.getProjectUrl());
        project.setCandidate(candidate);

        Project savedProject = projectRepository.save(project);
        ResponseStructure<Project> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Project added successfully");
        response.setData(savedProject);
        return response;
    }

    public ResponseStructure<String> deleteProject(Long candidateId, Long projectId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project with id " + projectId + " not found"));

        if (project.getCandidate() == null || !project.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Project does not belong to candidate id: " + candidateId);
        }

        projectRepository.delete(project);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Project deleted successfully");
        response.setData("Project " + projectId + " deleted for candidate " + candidateId);
        return response;
    }

    // ================= CERTIFICATES =================
    public ResponseStructure<Certificate> addCertificate(Long candidateId, CertificateDto certificateDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Certificate certificate = new Certificate();
        certificate.setName(certificateDto.getName());
        certificate.setIssuingOrganization(certificateDto.getIssuingOrganization());
        certificate.setIssueDate(certificateDto.getIssueDate());
        certificate.setCertificateUrl(certificateDto.getCertificateUrl());
        certificate.setCandidate(candidate);

        Certificate savedCertificate = certificateRepository.save(certificate);
        ResponseStructure<Certificate> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Certificate added successfully");
        response.setData(savedCertificate);
        return response;
    }

    public ResponseStructure<String> deleteCertificate(Long candidateId, Long certificateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate with id " + certificateId + " not found"));

        if (certificate.getCandidate() == null || !certificate.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Certificate does not belong to candidate id: " + candidateId);
        }

        certificateRepository.delete(certificate);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Certificate deleted successfully");
        response.setData("Certificate " + certificateId + " deleted for candidate " + candidateId);
        return response;
    }
}