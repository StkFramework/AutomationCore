package com.softtek.automation.element;

public class UIElement {

	private How how;
	private String using;
	private String value;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String name) {
		this.id = name;
	}

	public How getHow() {
		return how;
	}

	public void setHow(How how) {
		this.how = how;
	}

	public String getUsing() {
		return using;
	}

	public void setUsing(String using) {
		this.using = using;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
