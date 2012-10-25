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
package com.springsource.html5expense.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attachment")
public class Attachment implements Serializable{
	

	@Id
	private Long attachmentId;
	
	private String fileName;
	
	private byte[] content;
	
	private String contentType;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long id) {
		this.attachmentId = id;
	}

	public String getFileNmae() {
		return fileName;
	}

	public void setFileNmae(String fileNmae) {
		this.fileName = fileNmae;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Attachment(){
		
	}
	public Attachment(String fileName,String contentType,byte[] content){
		this.content = content;
		this.contentType = contentType;
		this.fileName = fileName;
	}

}
