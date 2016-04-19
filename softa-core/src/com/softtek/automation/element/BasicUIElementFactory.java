package com.softtek.automation.element;

import java.lang.reflect.Field;

import com.softtek.automation.anotations.ElementMap;

public class BasicUIElementFactory implements UIElementFactory {

	private String viewsSource;

	public String getViewsSource() {
		return viewsSource;
	}

	public void setViewsSource(String viewsSource) {
		this.viewsSource = viewsSource;
	}

	@Override
	public UIElement createElement(String idElement) throws Exception {

		UIElement resultElement = null;

		if (isValidFormat(idElement)) {

			resultElement = createUIElementInstance(processClassName(idElement), processClassFieldName(idElement));

		}

		return resultElement;
	}

	private boolean isValidFormat(String idElement) {
		return idElement.split(".").length == 2;
	}

	private String processClassFieldName(String idElement) {

		return idElement.split(".")[1];
	}

	private Class<?> processClassName(String idElement) throws Exception {

		String className = idElement.split(".")[0];

		Class<?> clazz;

		try {

			clazz = Class.forName(new StringBuilder(getViewsSource()).append(".").append(className).toString());

		}

		catch (ClassNotFoundException e) {
			throw new Exception(new StringBuilder("Failing UI element creation: \"")
					.append(idElement)
					.append("\" doesn't exists")
					.toString());
		}

		return clazz;
	}

	private UIElement createUIElementInstance(Class<?> clazz, String field) throws Exception {

		UIElement uiElement = null;

		Object viewStructure = clazz.newInstance();

		uiElement = getUIElementFromViewFields(field, viewStructure);

		return uiElement;
	}

	private UIElement getUIElementFromViewFields(String field, Object viewStructure) throws Exception {

		UIElement uiElement = null;

		Field[] fields = viewStructure.getClass().getDeclaredFields();

		for (Field f : fields) {
			try {

				if (field.equals(f.getName())) {

					f.setAccessible(true);

					ElementMap elementMap = f.getAnnotation(ElementMap.class);

					uiElement = new UIElement();

					uiElement.setHow(elementMap.how());
					uiElement.setId(f.getName());
					uiElement.setUsing(elementMap.using());
					uiElement.setValue(null);

				}

			}
			catch (Exception e) {
				throw new Exception("The field [" + f.getName() + "] in class ["
						+ viewStructure.getClass().getCanonicalName()
						+ "] can't be processed.", e);
			}
		}

		return uiElement;
	}

}
