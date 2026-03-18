package com.example.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.crypto.algorithms.DecryptManager;
import com.example.app.crypto.algorithms.EncryptManager;
import com.example.app.model.FileSpec;
import com.example.app.service.FileService;

@Controller
@RequestMapping("/api")
public class RestController {

	@Autowired
	private EncryptManager encryptManager;

	@Autowired
	private DecryptManager decryptManager;

	@GetMapping("/health")
	@ResponseBody
	public String health() {
		return "server=up,encrypt=" + encryptManager.getAlgorithm() + ",decrypt=" + decryptManager.getAlgorithm();
	}

	@PostMapping("/encrypt")
	@ResponseBody
	public String encrypt(@RequestPart("fileinfo") String fileinfo, @RequestPart("file") MultipartFile file) {
		FileSpec spec = new FileSpec();
		try {
			spec.setFromJSON(fileinfo);
			try (InputStream inputStream = file.getInputStream()) {
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				byte[] data = new byte[1024];
				int nRead;
				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				buffer.flush();
				spec.setFileContent(buffer.toByteArray());
				buffer.close();
				// FileService의 encryptFile 메서드를 호출하여 파일을 암호화합니다.
				FileService fileService = new FileService();
				fileService.encryptFile(spec.getFileContent(), spec.getPassword());
			} catch (Exception e) {
				return "{ \"error\": \"Error while reading file content\" }";
			}
		} catch (Exception e) {
			return "{ \"error\": \"Parse Error while processing JSON\" }";
		}
		return "{ \"mode\": \"encrypt\", \"algorithm\": \"" + encryptManager.getAlgorithm() + "\", \"filename\": \""
				+ spec.getFileName() + "\" }";
	}

	@PostMapping("/decrypt")
	@ResponseBody
	public String decrypt(@RequestParam("fileinfo") String fileinfo) {
		FileSpec spec = new FileSpec();
		try {
			spec.setFromJSON(fileinfo);
		} catch (Exception e) {
			return "{ \"error\": \"Parse Error while processing JSON\" }";
		}
		return "{ \"mode\": \"decrypt\", \"algorithm\": \"" + decryptManager.getAlgorithm() + "\", \"filename\": \""
				+ spec.getFileName() + "\" }";
	}

}
