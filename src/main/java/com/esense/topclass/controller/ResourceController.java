package com.esense.topclass.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esense.topclass.dbmodel.Content;
import com.esense.topclass.dbmodel.RandomCity;
import com.esense.topclass.dbmodel.User;
import com.esense.topclass.service.GenericService;


@RestController
@RequestMapping("/api/v1/")
public class ResourceController {
    @Autowired
    private GenericService userService;

    
    @RequestMapping(value ="/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<RandomCity> getUser(){
        return userService.findAllRandomCities();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
    
    @RequestMapping(value ="/loginuser", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Map loginUser(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	Map map = new HashMap();
    	User userDetail = userService.findByUsername(auth.getPrincipal().toString());
    	map.put("data", userDetail);
        return map;
    }
    
    @RequestMapping(value ="/getupdate", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Map<String, Object> getUpdate(@RequestParam("versionId") String versionId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Content> content = userService.findVersionUpdate(versionId);
    	map.put("data", content);
    	map.put("baseUrl", "http://192.168.1.200:8085/api/v1/imageAccess?imageUrl=");
        return map;
    	
    }
    
    @RequestMapping(value ="/getallupdate", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Content> getallupdate(){
    	List<Content> content = userService.findAllVersionUpdate();
    	return content;
    	
    }
    
    public static final String UPLOAD_DIRECTORY = "D:/content_files/";
    
    @RequestMapping(value = "/imageAccess", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public void showImage(@RequestParam("imageUrl") String imageUrl, HttpServletResponse response)
	{
		ServletOutputStream outStream;
		try
		{
			outStream = response.getOutputStream();
			File file = new File(UPLOAD_DIRECTORY, imageUrl);
			InputStream fis = new FileInputStream(file);
			ServletOutputStream os = response.getOutputStream();
			response.setContentType("application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			byte[] bufferData = new byte[1024];
			int read=0;
			while((read = fis.read(bufferData))!= -1){
				os.write(bufferData, 0, read);
			}
			os.flush();
			os.close();
			fis.close();
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
