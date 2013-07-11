package com.usms.application.objects;

import java.io.Serializable;

public class UsmsFile implements Serializable {
	   
		private static final long serialVersionUID = -8192553629588066292L;
	    private String name;
	    public String fileType;
	    public String ownerType;
	    //private String mime;
	    private long length;
	    private byte[] data;
	    
	    private String relativePath;
	    private String empNo;

	    public String getRelativePath() {
			return relativePath;
		}

		public void setRelativePath(String relativePath) {
			this.relativePath = relativePath;
		}
        
		

		

		public String getEmpNo() {
			return empNo;
		}

		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}

		public byte[] getData() {
	        return data;
	        
	    }

	    public void setData(byte[] data) {
	        this.data = data;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	       // int extDot = name.lastIndexOf('.');
//	        if (extDot > 0) {
//	            String extension = name.substring(extDot + 1);
//	            if ("bmp".equals(extension)) {
//	                mime = "image/bmp";
//	            } else if ("jpg".equals(extension)) {
//	                mime = "image/jpeg";
//	            } else if ("gif".equals(extension)) {
//	                mime = "image/gif";
//	            } else if ("png".equals(extension)) {
//	                mime = "image/png";
//	            } else {
//	                mime = "image/unknown";
//	            }
	        //}
	    	this.name = name;
	    	
	    	
	    }

	    public long getLength() {
	        return length;
	    }

	    public void setLength(long length) {
	        this.length = length;
	    }

//	    public String getMime() {
//	        return mime;
//	    }
	    

}
