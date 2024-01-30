package com.gustavonascimento.TesteEstagioDevTrainingProgram.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.Student;

@Service
public class GoogleSheetsService {

	// Constants for Google Sheets API authentication
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens/path";
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	// Spreadsheet details and Sheets service
	private final String spreadsheetId;
	private final String range;
	private final Sheets sheetsService;

	/**
	 * Constructor for GoogleSheetsService. Initializes the Sheets service with
	 * authentication.
	 *
	 * @param spreadsheetId The ID of the target spreadsheet.
	 * @param range         The range of data to retrieve or update in the
	 *                      spreadsheet.
	 * @throws IOException, GeneralSecurityException on authentication or IO errors.
	 */
	public GoogleSheetsService(@Value("${spreadsheet.id}") String spreadsheetId,
			@Value("${spreadsheet.range}") String range) throws IOException, GeneralSecurityException {
		this.spreadsheetId = spreadsheetId;
		this.range = range;

		NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		this.sheetsService = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Retrieves the credentials required for Google Sheets API authentication.
	 *
	 * @param HTTP_TRANSPORT The transport protocol for HTTP.
	 * @return A valid Credential for accessing the Google Sheets API.
	 * @throws IOException on IO errors.
	 */
	private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		InputStream in = GoogleSheetsService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new IOException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();

		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	/**
	 * Retrieves the data from the specified range in the target spreadsheet.
	 *
	 * @return A list of lists representing the spreadsheet data.
	 * @throws IOException on API request errors.
	 */
	public List<List<Object>> getSpreadsheetData() throws IOException {
		ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();

		return response.getValues();
	}

	/**
	 * Extracts and returns the total number of classes in the semester from the
	 * spreadsheet data.
	 *
	 * @param values The spreadsheet data containing information about the total
	 *               number of classes.
	 * @return The total number of classes in the semester.
	 */
	public int getTotalClassesSemester(List<List<Object>> values) {
		int numberClass = Integer.parseInt(values.get(1).toString().split(": ")[1].replaceAll("\\D", ""));
		return numberClass;
	}

	/**
	 * Updates the spreadsheet with the final approval grade and situation for each
	 * student.
	 *
	 * @param students The list of Student entities to be updated in the
	 *                 spreadsheet.
	 * @throws IOException on API request errors.
	 */
	public void updateSpreadsheet(List<Student> students) throws IOException {
		List<ValueRange> data = new ArrayList<>();

		// Iterate through students and prepare data for updating spreadsheet.
		for (int i = 0; i < students.size(); i++) {
			Student student = students.get(i);
			List<Object> row = Arrays.asList(student.getSituation(), student.getFinalApprovalGrade() * 10);
			String updateRange = range + "!G" + (i + 4) + ":H" + (i + 4);
			data.add(new ValueRange().setRange(updateRange).setValues(Arrays.asList(row)));
		}

		BatchUpdateValuesRequest batchUpdateValuesRequest = new BatchUpdateValuesRequest();
		batchUpdateValuesRequest.setData(data).setValueInputOption("RAW");

		// Execute batch update on the Google Sheets API.
		sheetsService.spreadsheets().values().batchUpdate(spreadsheetId, batchUpdateValuesRequest).execute();
	}

}
