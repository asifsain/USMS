package com.usms.businessagents;

import com.usms.view.model.EmployeeViewModel;


public class EmployeeRegistrationAgent {
	
	public boolean isFile = false;
	public boolean isComm = false;
	public boolean isSal = false;
	public boolean isFamily = false;
	public boolean isBank = false;
	
	private boolean prevStepValidationStatus = false;
	
	// mode field added  by Tariq
	private boolean mode= false;
	
	public boolean isPrevStepValidationStatus() {
		return prevStepValidationStatus;
	}

	public void setPrevStepValidationStatus(boolean prevStepValidationStatus) {
		this.prevStepValidationStatus = prevStepValidationStatus;
	}

	public String validateBeforeSubmit()
	{
		if(isFile)
			if(isComm)
				if(isSal)
					if(isFamily)
						if(isBank)
							return "Complete";
						else
							return "Bank";
					else
						return "Family";
				else
					return "Sal";
			else
				return "Comm";
		else
			return "File";
	}
	
	public boolean validateFileInfo(EmployeeViewModel model)
	{
		// Execute the validation logic
			// if Fail
				// can put out messages in the faces config
				// isFile = true;
				// prevStepValidationStatus = true;
				// return false
			// else
		
		isFile = true;
		prevStepValidationStatus = true;
		return true;
	}
	
	public boolean validateCommInfo(EmployeeViewModel model)
	{
		// can put out messages in the faces config
		isComm = true;
		prevStepValidationStatus = true;
		return true;
	}
	
	public boolean validateBankInfo(EmployeeViewModel model)
	{
		// can put out messages in the faces config
		isBank = true;
		prevStepValidationStatus = true;
		return true;
	}
	
	public boolean validateSalInfo(EmployeeViewModel model)
	{
		// can put out messages in the faces config
		isSal = true;
		prevStepValidationStatus = true;
		return true;
	}
	
	public boolean validateFamilyInfo(EmployeeViewModel model)
	{
		// can put out messages in the faces config
		isFamily = true;
		prevStepValidationStatus = true;
		return true;
	}

	public boolean isMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}
	
	

}
