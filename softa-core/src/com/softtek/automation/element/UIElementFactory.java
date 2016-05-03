package com.softtek.automation.element;



public interface UIElementFactory {

	// NOTA: UIElementFactory.createElement debe checar si el elemento ya fue creado, si no crear la asignar una nueva
	// instancia al atributo estatico (implementar singleton)

	void setViewsSource(String source);	
	
	UIElement createElement(String element) throws Exception;

}
