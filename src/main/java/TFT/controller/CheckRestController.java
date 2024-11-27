package TFT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import TFT.service.FileDelService;
import jakarta.servlet.http.HttpSession;

@RestController
public class CheckRestController {
	@Autowired
	FileDelService fileDelService;

	@PostMapping("/file/fileDel")
	public int fileDel(String orgFile, String storeFile, HttpSession session) {
		return fileDelService.execute(orgFile, storeFile, session);
	}
}