package com.phobes.cloudDisk.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phobes.cloudDisk.Application;
import com.phobes.cloudDisk.domain.CurrentUser;

@Controller
public class FileUploadController {

	@RequestMapping(method = RequestMethod.GET, value = "/upload")
	public String provideUploadInfo(Model model, HttpServletRequest request) {
		String currentPath = new Path().getCurrentBasePath(request);
		System.out.println(currentPath);
		File rootFolder = new File(currentPath);
		//过滤器判断是否是文件
		FileFilter isfile = new FileFilter() {
			            
			            @Override
			            public boolean accept(File pathname) {
			                // TODO Auto-generated method stub
			               return pathname.isFile();
			                
			            }
			        };
			    	//过滤器判断是否是目录
					FileFilter isDir = new FileFilter() {
				            
				            @Override
				            public boolean accept(File pathname) {
				                // TODO Auto-generated method stub
				               return pathname.isDirectory();
				                
				            }
				 };
				List<String> fileNames = Arrays.stream(rootFolder.listFiles(isfile))
					.map(f -> f.getName())
					.collect(Collectors.toList());

				model.addAttribute("files",
					Arrays.stream(rootFolder.listFiles(isfile))
							.sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
							.map(f -> f.getName())
							.collect(Collectors.toList())
				);
				model.addAttribute("directorys",
						Arrays.stream(rootFolder.listFiles(isDir))
								.sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
								.map(f -> f.getName())
								.collect(Collectors.toList())
					);
		return "uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	public String handleFileUpload(@RequestParam("name") String name,
								   @RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
			return "redirect:upload";
		}
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
			return "redirect:upload";
		}

		if (!file.isEmpty()) {
			try {
				String username = this.getAuthenticatedUsername();
				System.out.println("username" + username);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(Application.ROOT + "/" + username +"/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + name + "!");
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"You failed to upload " + name + " => " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("message",
					"You failed to upload " + name + " because the file was empty");
		}

		return "redirect:upload";
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