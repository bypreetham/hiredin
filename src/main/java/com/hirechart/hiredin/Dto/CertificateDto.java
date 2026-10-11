package com.hirechart.hiredin.Dto;

public class CertificateDto {

    private String name;
    private String issuingOrganization;
    private String issueDate;
    private String certificateUrl;

    public CertificateDto() {
        super();
    }

    public CertificateDto(String name, String issuingOrganization, String issueDate, String certificateUrl) {
        super();
        this.name = name;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
        this.certificateUrl = certificateUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }
}
