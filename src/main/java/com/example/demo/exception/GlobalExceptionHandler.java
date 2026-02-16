package com.example.demo.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(CsrfException.class)
	public String cerfExceptionHandler(CsrfException ex,RedirectAttributes redirectAttributes) {
		Log.warn("CSRF validation failed",ex.getMessage());
		redirectAttributes.addFlashAttribute("message" , "session expired,please log on again");
		return "redirect:/login";
    }
	 @ExceptionHandler(Exception.class)
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String genericExceptionHandler(Exception ex) {
		 Log.error("CSRF validation failed" , ex.getMessage());
		return "500";
	}


}
