package com.usms.helper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.usms.application.objects.UsmsFile;

public class USMSFileUploadHelper
{

	/** To save files in FileRoot/<Type_Of_File>/<Emp_No>/<Owner_Type>/<File_Name>.EXT 
	 * Type_Of_File - miCard | eidCard | LabourCard | Visa | Passport 
	 * Owner_Type - Employee | Spouse | Child1 | Child2
	 * 
	 * Return - Relative Path Of The Saved File 
	 * */
	public static String saveUsmsFile(UsmsFile file, String filesRoot)
	{
		String relativePath = "";
		File dir = new File(filesRoot + File.separator + file.fileType+File.separator +file.getEmpNo()+File.separator+file.ownerType); 
		String filename=file.getName();
		try {
			
			dir.mkdirs();
			//	File newPassportFile = new File(filesRoot+File.separator+"passport"+File.separator+model.getEmpInfo().getEmpNo()+".png");
		  	//	newPassportFile.createNewFile();
			//----file.getName() added by Tariq
			
			File newFile = new File(dir,filename);
			FileOutputStream stream = new FileOutputStream(newFile);
			
			stream.write(file.getData());    
	        stream.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			relativePath =dir+File.separator+filename;
		}
		
		return relativePath;
		
	}
	
	public static UsmsFile createUsmsFileData(UsmsFile file, FileUploadEvent event)
	{
		UploadedFile rawFile = event.getUploadedFile();
		
		file.ownerType = (String)event.getComponent().getAttributes().get("ownerType");
		file.fileType = (String)event.getComponent().getAttributes().get("fileType");
		
		file.setLength(rawFile.getData().length);
		file.setName(rawFile.getName());
		file.setData(rawFile.getData());
		
		return file;
	}
	
}
