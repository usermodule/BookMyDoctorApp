package com.nv.doctorapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nv.doctorapp.dto.DoctorResponseDTO;
import com.nv.doctorapp.entity.Doctor;
import com.nv.doctorapp.service.IDoctorService;
import com.nv.doctorapp.util.DoctorDTOConvertor;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IDoctorService doctorService;

	@Autowired
	DoctorDTOConvertor dtoConvertor;

	public DoctorRestController() {
		logger.info(" Doctor Rest Controller Called");
		System.err.println("-------Doctor Rest Controller Called-------");
	}

	@PostMapping("/add")
	public ResponseEntity<DoctorResponseDTO> saveDoctor(@RequestBody Doctor doctor) throws Exception {

		Doctor savedDoctor = doctorService.addDoctor(doctor);
		logger.info("Doctor Saved" + savedDoctor);

		DoctorResponseDTO dto = dtoConvertor.convertTo(savedDoctor);
		
		return new ResponseEntity<DoctorResponseDTO>(dto, HttpStatus.OK);

	}

	@GetMapping("/list")
	public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {

		List<Doctor> allDoctors = doctorService.getAllDoctors();
		List<DoctorResponseDTO> dtoList = new ArrayList<>();

		for (Doctor doctor : allDoctors) {

			DoctorResponseDTO dtoObj = dtoConvertor.convertTo(doctor);
			dtoList.add(dtoObj);
		}
		return new ResponseEntity<List<DoctorResponseDTO>>(dtoList, HttpStatus.OK);
	}
	
	@GetMapping("/getDoctor/{doctorId}")
	public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable int doctorId) {
		
		Doctor savedDoctor = doctorService.getDoctorById(doctorId);
		DoctorResponseDTO dto = dtoConvertor.convertTo(savedDoctor);
		return new ResponseEntity<DoctorResponseDTO>(dto, HttpStatus.OK);
	}
	
	/*@GetMapping("/custom/{location}")
	public ResponseEntity<DoctorResponseDTO> getDoctorByLocation(@PathVariable String Location){
		
		Doctor savedDoctor = customRepository.getDoctorByLocation(Location);
		DoctorResponseDTO dto = dtoConvertor.convertTo(savedDoctor);
		return new ResponseEntity<DoctorResponseDTO>(dto, HttpStatus.OK);
		
	}*/
	
	@PutMapping("/getDoc/{doctorId}")
	public String updateDoctor(@PathVariable int doctorId){
		Doctor updatedDoctor = doctorService.getDoctorById(doctorId);
		return updatedDoctor.toString();
		
	}
	
	@DeleteMapping("/deleteDoctor/{doctorId}")
	public void removeDoctor(@PathVariable int doctorId) {
		doctorService.removeDoctorById(doctorId);
	}
}
