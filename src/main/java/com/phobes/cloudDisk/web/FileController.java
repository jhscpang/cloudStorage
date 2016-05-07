package com.phobes.cloudDisk.web;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



/*
 * 
 * @author phobes
**/
//文件夹和文件的新建，删除，移动

@Controller
@RequestMapping("/filefold")
public class FileController {
	
	
    @RequestMapping(
            method=RequestMethod.GET)  
    @ResponseStatus(HttpStatus.OK)  
  
	public String getFiles(Model model,
			 HttpSession session, HttpServletRequest request
			){
    	String currentBasePath = null;
    	
    	if(session.getAttribute("currentPath") == null){
    		currentBasePath = new Path().getCurrentBasePath(request);		
    		session.setAttribute("currentPath", currentBasePath);
//    		System.out.println("Session id is :"+ session.getId());
    	}
    	currentBasePath=(String )session.getAttribute("currentPath");
    	System.out.println(currentBasePath);
    	
		File rootFolder = new File(currentBasePath);
		
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
		return "files";
	}
    
    @RequestMapping(value = "/{path}",
            method=RequestMethod.GET)  
    @ResponseStatus(HttpStatus.OK)  
	public String getFiles(@PathVariable String path, HttpSession session, Model model){
		String currentBasePath = (String) session.getAttribute("currentPath") + "/" + path;
		return "redirect:filefold";
	}
    
    /*
     * 
     * 新建文件夹
     * */
    @RequestMapping(
            method=RequestMethod.POST)  
    @ResponseStatus(HttpStatus.OK)  
	public String addDir(@RequestParam String path,
		HttpSession session){
    
    	System.out.println("add dir Session id is " + session.getId());

		String currentPath = (String)session.getAttribute("currentPath") + "/" + path;
		System.out.println("add dir Session is " + session.getAttribute("currentPath"));
		new File(currentPath).mkdir();
		
		return "redirect:filefold";
	}
  
}
