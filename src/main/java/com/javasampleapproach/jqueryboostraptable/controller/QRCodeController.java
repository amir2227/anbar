package com.javasampleapproach.jqueryboostraptable.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.javasampleapproach.jqueryboostraptable.model.Tajhizat;
import com.javasampleapproach.jqueryboostraptable.repository.TajhizatRepository;

@Controller
public class QRCodeController {
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/images/QRCode.png";

	@Autowired
	private TajhizatRepository tRepo;
	
    @GetMapping(value = "/genrateAndDownloadQRCode")
		public void download(String codeText)
			    throws Exception {
			        QRCodeGenerator.generateQRCodeImage(codeText, 350, 350, QR_CODE_IMAGE_PATH);
			        
			    }

    @GetMapping(value = "fa/genrateQRCode")
   	public String generateQRCode(
   			 String codeText)
   	
   		    throws Exception {
   		byte[] data = QRCodeGenerator.getQRCodeImage(codeText, 350, 350);
   		String encodedString = Base64.getEncoder().encodeToString(data);
   		Tajhizat t = tRepo.findById(Integer.parseInt(codeText)).get();
   		t.setQrcode(encodedString);
   		tRepo.save(t);
   		        return "redirect:/fa/tajhizats";
   		    }
}

