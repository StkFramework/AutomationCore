/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.element;



public interface UIElementFactory {

	// NOTA: UIElementFactory.createElement debe checar si el elemento ya fue creado, si no crear la asignar una nueva
	// instancia al atributo estatico (implementar singleton)

	void setViewsSource(String source);	
	
	UIElement createElement(String element) throws Exception;

}
