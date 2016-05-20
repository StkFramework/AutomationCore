package com.softtek.automation;

import java.util.List;

public class ApplicationsSet {

	private List<Application> applications;

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public void addApplication(Application application) {

		getApplications().add(application);
	}

}
