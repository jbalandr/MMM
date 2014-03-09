package minimed.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.S3Link;

@DynamoDBTable(tableName = "Accounts")
public class Account {

	//Fields in Dynamo
	private String username;
	private S3Link userData;
	private S3Link contactData;
	private S3Link allergyData;
	private S3Link medicationData;
	private S3Link historyData;
	private S3Link imageData;
	
	//From s3 links
	private User user;
	private List<Allergy> allergies;
	private List<Contact> contacts;
	private List<Medication> medication;
	private List<MedicalHistory> medHistory;
	//private List<Image> images;
	
	public Account(String username) {
		this.username = username;
	}
	
    @DynamoDBHashKey
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public S3Link getUserData() {
		return userData;
	}

	public void setUserData(S3Link userData) {
		this.userData = userData;
	}

	public S3Link getContactData() {
		return contactData;
	}

	public void setContactData(S3Link contactData) {
		this.contactData = contactData;
	}

	public S3Link getAllergyData() {
		return allergyData;
	}

	public void setAllergyData(S3Link allergyData) {
		this.allergyData = allergyData;
	}

	public S3Link getMedicationData() {
		return medicationData;
	}

	public void setMedicationData(S3Link medicationData) {
		this.medicationData = medicationData;
	}

	public S3Link getHistoryData() {
		return historyData;
	}

	public void setHistoryData(S3Link historyData) {
		this.historyData = historyData;
	}

	public User getUser() {
		return user;
	}

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Medication> getMedication() {
		return medication;
	}

	public List<MedicalHistory> getMedHistory() {
		return medHistory;
	}
    
	public void updateContacts(List<Contact> newContacts) throws IOException {
		List<String> jsonList = new ArrayList<String>();
		
		for(Contact cont : newContacts) {
			jsonList.add(cont.getJSONString());
		}
		
		Accessor.putToS3(contactData, jsonList, username, FieldNames.S3BucketKeys.contactDataFormat);
	}
	
}
