package com.phobes.cloudDisk.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phobes.cloudDisk.Application;
import com.phobes.cloudDisk.domain.CurrentUser;
@Controller
public class FileDownloadController {
	@RequestMapping(value = "/files", method = RequestMethod.GET)
    public String getFilesDownload(){
        
       return "filesdownload";
    }
	@RequestMapping(value = "/files/{filename}", method = RequestMethod.GET)
    public void getFile(
        @PathVariable("filename") String fileName, 
        HttpServletResponse response) throws IOException {
	
            InputStream is = new FileInputStream(Application.ROOT + "/" + fileName);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
    }
	 public static String getAuthenticatedUsername() { 
		    String username = null; 
		    Object principal = SecurityContextHolder.getContext() 
		        .getAuthentication().getPrincipal(); 
		    if (principal instanceof CurrentUser) { 
		        username = ((CurrentUser) principal).getUsername(); 
		    } else { 
		        username = principal.toString(); 
		    } 
		    return username; 
		 } 
}
