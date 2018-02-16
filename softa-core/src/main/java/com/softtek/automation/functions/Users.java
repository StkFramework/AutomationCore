package com.softtek.automation.functions;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Users {
    
	private String name1stLevel;
	private String name2ndLevel;
    private String role;
    private String location;
    private String email;
    private String area;
    private String phone;
    private String ext;
 
 
    public Users() {
    }
 
    public Users(String name1stLevel, String name2ndLevel, String role, String location, String area, String phone, String ext, String email) {
        this.name1stLevel = name1stLevel;
        this.name2ndLevel = name2ndLevel;
        this.role = role;
        this.location = location;
        this.area = area;
        this.phone = phone;
        this.ext = ext;
        this.email = email;
    }

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the name1stLevel
	 */
	public String getName1stLevel() {
		return name1stLevel;
	}

	/**
	 * @return the name2ndLevel
	 */
	public String getName2ndLevel() {
		return name2ndLevel;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param name1stLevel the name1stLevel to set
	 */
	public void setName1stLevel(String name1stLevel) {
		this.name1stLevel = name1stLevel;
	}

	/**
	 * @param name2ndLevel the name2ndLevel to set
	 */
	public void setName2ndLevel(String name2ndLevel) {
		this.name2ndLevel = name2ndLevel;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
  
}
