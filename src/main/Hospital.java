package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Patient;

public class Hospital {
	private static final String LOW = "Baja";
	private static final String MEDIUM = "Media";
	private static final String HIGH = "HIGH";
	private static ArrayList<Patient> patients =  new ArrayList<>();
	
	public static void main(String[] args) {
		Hospital hospital = new Hospital();
		
		hospital.loadPatients();
		for (Patient patient : patients) {
			System.out.println(patient);
		}
		
		hospital.writeReportByLevel();
	}
	
	/**
	 * read patients from file
	 */
	private void loadPatients() {
		// locate file, path and name
		File f = new File(System.getProperty("user.dir") + File.separator + "files/inputPatients.txt");
		
		try {			
			// wrap in proper classes
			FileReader fr;
			fr = new FileReader(f);				
			BufferedReader br = new BufferedReader(fr);
			
			// read header line to exclude it 
			String line = br.readLine();
						
			// read first line with patient data 
			line = br.readLine();
			
			// process and read next line until end of file
			while (line != null) {
				// split in sections
				String[] data = line.split(";");
				
				Patient patient = new Patient();
				
				// read each sections
				for (int i = 0; i < data.length; i++) {
					
					// format value
					switch (i) {
					case 0:
						// format patient code
						patient.setCode(Integer.parseInt(data[0]));
						break;
						
					case 1:
						// format name
						patient.setName(data[1]);
						break;
						
					case 2:
						// format age
						patient.setAge(Integer.parseInt(data[2]));
						break;
						
					case 3:
						// format sickness
						patient.setSickness(data[3]);
						break;
						
					case 4:
						// format level
						patient.setLevel(data[4]);
						break;
					
					case 5:
						// format report
						patient.setReport(data[4]);
						break;
						
					default:
						break;
					}
				}
				// add patient to list of patients from hospital
				patients.add(patient);
				
				// read next line
				line = br.readLine();
			}
			fr.close();
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write in file the sum of patients group by sickness level
	 */
	private void writeReportByLevel() {
		// define date
		LocalDateTime date = LocalDateTime.now();		
		
		// locate file, path and name
		File f = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "outputPatients.txt");
				
		try {
			// wrap in proper classes
			FileWriter fw;
			fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			
			// write line by line
			// write header
			StringBuilder headerLine = new StringBuilder("Reporte Pacientes por gravedad a fecha " + date + ";");
			pw.write(headerLine.toString());
			pw.write("\n");			
			
			// write details
			int counterLow=0;
			int counterMedium=0;
			int counterHigh=0;			
			for (Patient patient : patients) {
				
				// group by sickness level value
				switch (patient.getLevel()) {
				case LOW:
					++counterLow;
					break;
					
				case MEDIUM:
					++counterMedium;
					break;
					
				case HIGH:
					++counterHigh;
					break;

				default:
					break;
				}
			}			
			// write low level
			StringBuilder lowLine = new StringBuilder("Gravedad Baja;Pacientes:" + counterLow + ";");
			pw.write(lowLine.toString());
			pw.write("\n");
			
			// write medium level
			StringBuilder mediumLine = new StringBuilder("Gravedad Media;Pacientes:" + counterMedium + ";");
			pw.write(mediumLine.toString());
			pw.write("\n");
			
			// write high level
			StringBuilder highLine = new StringBuilder("Gravedad Alta;Pacientes:" + counterHigh + ";");
			pw.write(highLine.toString());
			pw.write("\n");
			
			//write total patients
			StringBuilder footerLine = new StringBuilder("Total número pacientes:" + patients.size() + ";");
			pw.write(footerLine.toString());
						
			// close files
			pw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
}

