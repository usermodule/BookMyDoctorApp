package com.nv.doctorapp.controller.patient;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nv.doctorapp.dto.patient.PatientResponseDTO;
import com.nv.doctorapp.entity.Patient;
import com.nv.doctorapp.service.patient.IPatientService;
import com.nv.doctorapp.util.patient.PatientDTOConvertor;

@RestController
@RequestMapping("/patient")
public class PatientRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IPatientService patientService;

	@Autowired
	PatientDTOConvertor dtoConvertor;

	public PatientRestController() {
		logger.info(" Patient Rest Controller Called)");
		System.out.println("----Patient Rest Controller Called----");
	}

	@PostMapping("/register")
	public ResponseEntity<PatientResponseDTO> savePatient(@RequestBody Patient patient) throws Exception {

		Patient savedPatient = patientService.registerPatient(patient);
		logger.info("Patient saved" + savedPatient);

		PatientResponseDTO dto = dtoConvertor.convertTo(savedPatient);

		return new ResponseEntity<PatientResponseDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {

		List<Patient> allPatients = patientService.getAllPatients();
		List<PatientResponseDTO> dtoList = new ArrayList<>();

		for (Patient patient : allPatients) {

			PatientResponseDTO dtoObj = dtoConvertor.convertTo(patient);
			dtoList.add(dtoObj);
		}
		return new ResponseEntity<List<PatientResponseDTO>>(dtoList, HttpStatus.OK);
	}

	@GetMapping("/id/{patientId}")
	public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable int patientId) {

		Patient allPatients = patientService.getPatientById(patientId);
		PatientResponseDTO dto = dtoConvertor.convertTo(allPatients);
		return new ResponseEntity<PatientResponseDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/city/{patientCity}")
	public ResponseEntity<List<PatientResponseDTO>> getPatientByCity(@PathVariable String patientCity) {

		List<Patient> allPatients = patientService.getPatientByCity(patientCity);
		List<PatientResponseDTO> dtoObj = new ArrayList<>();

		for (Patient patient : allPatients)
			dtoObj.add(dtoConvertor.convertTo(patient));

		return new ResponseEntity<List<PatientResponseDTO>>(dtoObj, HttpStatus.OK);
	}
	
	@GetMapping("/name/{patientName}")
	public ResponseEntity<List<PatientResponseDTO>> getPatientByNmae(@PathVariable String patientName) {

		List<Patient> allPatients = patientService.getPatientByName(patientName);
		List<PatientResponseDTO> dtoObj = new ArrayList<>();

		for (Patient patient : allPatients)
			dtoObj.add(dtoConvertor.convertTo(patient));

		return new ResponseEntity<List<PatientResponseDTO>>(dtoObj, HttpStatus.OK);
	}

	@DeleteMapping("/deletePatient/{patientId}")
	public void removePatient(@PathVariable int patientId) {
		patientService.removePatientDetailsById(patientId);
	}

}