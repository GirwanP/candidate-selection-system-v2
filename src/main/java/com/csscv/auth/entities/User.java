package com.csscv.auth.entities;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;
    
    @Column(unique=true)
    private String email;

    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Role> roles;
    
    
    
    private boolean active;
    private boolean profileCreated;
    
    private String latestResetCode;
    private boolean passwordResetInitiated;
    
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLatestResetCode() {
		return latestResetCode;
	}

	public void setLatestResetCode(String latestResetCode) {
		this.latestResetCode = latestResetCode;
	}

	public boolean isPasswordResetInitiated() {
		return passwordResetInitiated;
	}

	public void setPasswordResetInitiated(boolean passwordResetInitiated) {
		this.passwordResetInitiated = passwordResetInitiated;
	}

	public boolean isProfileCreated() {
		return profileCreated;
	}

	public void setProfileCreated(boolean profileCreated) {
		this.profileCreated = profileCreated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
