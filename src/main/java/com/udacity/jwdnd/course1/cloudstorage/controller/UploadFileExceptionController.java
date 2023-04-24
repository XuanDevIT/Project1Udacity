package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class UploadFileExceptionController {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleFileTooLarge(MaxUploadSizeExceededException exception, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("result");
		modelAndView.getModel().put("uploadError", true);
		modelAndView.getModel().put("uploadErrorMessage", "Your upload file must be less than 128KB");
		return modelAndView;
	}

}
