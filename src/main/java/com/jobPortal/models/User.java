package com.jobPortal.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String name;
    @Column
    String email;
    @Column
    String password;
    @Column
    String mobile;
    @Column
    Double otp;
    @Column
    Long otpExpirationTime;
    @Column
    Boolean isActive;
    @Column
    Role role;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated;
    @UpdateTimestamp
    Timestamp lastModified;

    public Long getId() {
        return id;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getOtp() {
        return otp;
    }

    public void setOtp(Double otp) {
        this.otp = otp;
    }

    public Long getOtpExpirationTime() {
        return otpExpirationTime;
    }

    public void setOtpExpirationTime(Long otpExpirationTime) {
        this.otpExpirationTime = otpExpirationTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
