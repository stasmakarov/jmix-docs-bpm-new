package com.company.onboarding.entity;

import io.jmix.core.FileRef;
import io.jmix.core.HasTimeZone;
import io.jmix.core.annotation.Secret;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.security.authentication.JmixUserDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Entity
@Table(name = "USER_", indexes = {
        @Index(name = "IDX_USER__ON_USERNAME", columnList = "USERNAME", unique = true),
        @Index(name = "IDX_USER__DEPARTMENT", columnList = "DEPARTMENT_ID")
})
public class User implements JmixUserDetails, HasTimeZone {

    @Id
    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    private UUID id;

    @Column(name = "SALARY")
    private Float salary;

    @Column(name = "BONUS")
    private Float bonus;

    @Column(name = "HARD_SKILLS")
    private Integer hardSkills;

    @Column(name = "SOFT_SKILLS")
    private Integer softSkills;

    @Column(name = "TEAMWORK")
    private Integer teamwork;

    @Column(name = "PROBLEM_SOLVING")
    private Integer problemSolving;

    @Column(name = "ASSESSMENT_TOTAL")
    private Integer assessmentTotal;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "USERNAME", nullable = false)
    protected String username;

    @Secret
    @SystemLevel
    @Column(name = "PASSWORD")
    protected String password;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Email
    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "ACTIVE")
    protected Boolean active = true;

    @Column(name = "TIME_ZONE_ID")
    protected String timeZoneId;

    @Column(name = "ONBOARDING_STATUS")
    private Integer onboardingStatus;

    @JoinColumn(name = "DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OrderBy("sortValue")
    @Composition
    @OneToMany(mappedBy = "user")
    private List<UserStep> steps;

    @Column(name = "JOINING_DATE")
    private LocalDate joiningDate;

    @Column(name = "PICTURE", length = 1024)
    private FileRef picture;

    @Transient
    protected Collection<? extends GrantedAuthority> authorities;

    public Integer getAssessmentTotal() {
        return assessmentTotal;
    }

    public void setAssessmentTotal(Integer assessmentTotal) {
        this.assessmentTotal = assessmentTotal;
    }

    public Integer getProblemSolving() {
        return problemSolving;
    }

    public void setProblemSolving(Integer problemSolving) {
        this.problemSolving = problemSolving;
    }

    public Integer getTeamwork() {
        return teamwork;
    }

    public void setTeamwork(Integer teamwork) {
        this.teamwork = teamwork;
    }

    public Integer getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(Integer softSkills) {
        this.softSkills = softSkills;
    }

    public Integer getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(Integer hardSkills) {
        this.hardSkills = hardSkills;
    }

    public Float getBonus() {
        return bonus;
    }

    public void setBonus(Float bonus) {
        this.bonus = bonus;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public FileRef getPicture() {
        return picture;
    }

    public void setPicture(FileRef picture) {
        this.picture = picture;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public List<UserStep> getSteps() {
        return steps;
    }

    public void setSteps(List<UserStep> steps) {
        this.steps = steps;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public OnboardingStatus getOnboardingStatus() {
        return onboardingStatus == null ? null : OnboardingStatus.fromId(onboardingStatus);
    }

    public void setOnboardingStatus(OnboardingStatus onboardingStatus) {
        this.onboardingStatus = onboardingStatus == null ? null : onboardingStatus.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : Collections.emptyList();
    }

    @Override
    public void setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(active);
    }

    @InstanceName
    @DependsOnProperties({"firstName", "lastName", "username"})
    public String getDisplayName() {
        return String.format("%s %s [%s]", (firstName != null ? firstName : ""),
                (lastName != null ? lastName : ""), username).trim();
    }

    @Override
    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(final String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
}