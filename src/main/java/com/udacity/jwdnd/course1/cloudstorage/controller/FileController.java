package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.FileServiceImpl;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class FileController {
	@Autowired
	private FileServiceImpl fileService;

	@PostMapping("/upload")
	public String uploadFile(Authentication authentication, FileForm fileForm, Model model) throws SizeLimitExceededException {
		if (fileForm.getFileData().isEmpty()) {
			model.addAttribute("uploadError", true);
			model.addAttribute("uploadErrorMessage", "Please choose your file!");
			return "result";
		}

		try {
			int rowsAdd = fileService.uploadFile(authentication, fileForm);

			if (rowsAdd == 0) {
				model.addAttribute("uploadFailed", true);
				model.addAttribute("uploadFailedMessage", "File name has exist.");
			} else {
				model.addAttribute("uploadSucceeded", true);
			}
		} catch (IOException e) {
			model.addAttribute("uploadError", true);
			model.addAttribute("uploadErrorMessage", "File upload error");
			e.printStackTrace();
		}

		return "result";
	}

	@GetMapping("/file/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
		File file = fileService.getFileByFileId(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
				.body(file.getFileData());
	}

	@GetMapping("/file/delete/{id}")
	public String deleteFile(@PathVariable Integer id) {
		fileService.deleteFile(id);
		return "redirect:/home";
	}
}
