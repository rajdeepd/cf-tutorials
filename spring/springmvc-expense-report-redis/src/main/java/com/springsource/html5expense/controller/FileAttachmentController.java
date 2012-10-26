/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.html5expense.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.service.AttachmentService;
import com.springsource.html5expense.service.ExpenseService;



@Controller
public class FileAttachmentController {

	 	@Autowired
	    private ExpenseService expenseService;
	 	
	 	@Autowired
	 	private AttachmentService attachmentService;
	     	 
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(@RequestParam("file") MultipartFile file) {
	        try {
	            String fileName =file.getOriginalFilename();
	            String contentType = file.getContentType();
	            Attachment attachment = new Attachment(fileName, contentType, file.getBytes());
	            attachmentService.save(attachment);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "redirect:/";
	    }
	    
	    @RequestMapping(value="/download")
	    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    	String attachmentId = request.getParameter("attachmentId");
	    	         Attachment  attachment = this.attachmentService.getAttachment(new Long(attachmentId));
	    	         response.setContentType(attachment.getContentType());
	    	         response.setHeader("Content-Disposition","attachment; filename=\"" + attachment.getFileName() +"\"");
	    	         response.setHeader("cache-control", "must-revalidate");
	    	         OutputStream out = response.getOutputStream();
	    	         out.write(attachment.getContent());
	    	         out.flush();

	    }
	      
	
}
