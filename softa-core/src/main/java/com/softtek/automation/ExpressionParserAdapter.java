/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ExpressionParserAdapter {

	public static final String EXPRESSION_PATTERN = "^(([#][\\{])(cacheContext|volatileContext)([\\[][\\'])([_A-Za-z0-9]+)([\\'][]])([\\.][_A-Za-z0-9]+)*([\\}]))$";
	
	public String parseExpression(String expression, Object root){		
		
		String result = null;
				
		if(isValidFormat(expression)){
		
			ExpressionParser parser = new SpelExpressionParser();
			
			Expression expression_ = parser.parseExpression(expression, new TemplateParserContext());
				
			Object object = expression_.getValue(root);
							
			if(object instanceof String){
					
				result = (String) object;
			}else{
					//throw new IllegalArgumentException
			}
		}		
		
		return result;		
	}	
	
	
	private boolean isValidFormat(String text){		
		Pattern pattern = Pattern.compile(EXPRESSION_PATTERN);
		Matcher matcher = pattern.matcher(text);		
		return matcher.find();
	}
	
	
	public static void main(String ...args){
		
		ExpressionParserAdapter e = new ExpressionParserAdapter();
		
		System.out.println("e.isValidFormat() ->" + e.isValidFormat("#{volatileContext['PASS']}"));
		
		ExecutionContext ctx = new ExecutionContext();
		ctx.init();
		
		ctx.putElement("PASS", "sphase2015");
		ctx.putElement("USER", "admin");
		
		ctx.putElementOnCache("PASS2", "sphase");
		ctx.putElementOnCache("USER2", "adm");
		
		
		String result1 = e.parseExpression("#{volatileContext['USER']}", ctx);
		String result2 = e.parseExpression("#{volatileContext['PASS']}", ctx);
		
		System.out.println("result1 -> " + result1) ;
		System.out.println("result2 -> " + result2) ;
		
		result1 = e.parseExpression("#{cacheContext['USER2']}", ctx);
		result2 = e.parseExpression("#{cacheContext['PASS2']}", ctx);
		
		System.out.println("result1 -> " + result1) ;
		System.out.println("result2 -> " + result2) ;
		
		
	}
		
	class TemplateParserContext implements ParserContext {

	    public String getExpressionPrefix() {
	        return "#{";
	    }

	    public String getExpressionSuffix() {
	        return "}";
	    }

	    public boolean isTemplate() {
	        return true;
	    }
	}
}
