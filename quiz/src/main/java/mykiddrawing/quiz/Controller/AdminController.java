package mykiddrawing.quiz.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import mykiddrawing.quiz.exception.ResourceNotFoundException;
import mykiddrawing.quiz.model.Admin;
import mykiddrawing.quiz.repository.AdminRepository;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/admins")
    public List < Admin > getAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/admins/{admin_id}")
    public ResponseEntity < Admin > getAdminById(
        @PathVariable(value = "admin_id") Long admin_admin_id) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_admin_id)
            .orElseThrow(()-> new ResourceNotFoundException("Admin not found :: " + admin_admin_id));
        return ResponseEntity.ok().body(admin);
    }

    @PostMapping("/admin")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
    	UUID apitoken=UUID.randomUUID();
    	admin.setApitoken(apitoken.toString());
        return adminRepository.save(admin);
    }

    @PutMapping("/admins/{admin_id}")
    public ResponseEntity < Admin > updateAdmin(
        @PathVariable(value = "admin_id") Long admin_admin_id,
        @Valid @RequestBody Admin adminDetails) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_admin_id)
            .orElseThrow(()-> new ResourceNotFoundException("Admin not found :: " + admin_admin_id));
        
        admin.setAdmin_name(adminDetails.getAdmin_name());
        admin.setAdmin_mail(adminDetails.getAdmin_mail());
        final Admin updatedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/admins/{admin_id}")
    public Map < String, Boolean > deleteAdmin(
        @PathVariable(value = "admin_id") Long admin_admin_id) throws mykiddrawing.quiz.exception.ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_admin_id)
            .orElseThrow(()-> new ResourceNotFoundException("Admin not found :: " + admin_admin_id));

        adminRepository.delete(admin);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

