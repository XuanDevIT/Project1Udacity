package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
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

	private FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/upload")
	public String uploadFile(Authentication authentication, FileForm fileForm, Model model) throws SizeLimitExceededException {
		// Check the upload file is empty
		if (fileForm.getFileData().isEmpty()) {
			model.addAttribute("uploadError", true);
			model.addAttribute("uploadErrorMessage", "Please choose your file!");
			return "result";
		}
		
		// Upload file progress
		try {
			int rowsAdded = fileService.uploadFile(authentication, fileForm);

			if (rowsAdded == 0) {
				model.addAttribute("uploadFailed", true);
				model.addAttribute("uploadFailedMessage", "File name is already existed.");
			} else {
				model.addAttribute("uploadSucceeded", true);
			}
		} catch (IOException e) {
			model.addAttribute("uploadError", true);
			model.addAttribute("uploadErrorMessage", "There is something goes wrong in the uploading process");
			e.printStackTrace();
		}

		return "result";
	}

	@GetMapping("/file/download/{fileId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId) {
		File file = fileService.getFileByFileId(fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
				.body(file.getFileData());
	}

	@GetMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable Integer fileId) {
		fileService.deleteFileByFileId(fileId);
		return "redirect:/home";
	}
}
