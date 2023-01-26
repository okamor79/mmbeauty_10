package com.okamor.mmbeauty.controller;

import com.okamor.mmbeauty.model.Client;
import com.okamor.mmbeauty.model.Course;
import com.okamor.mmbeauty.model.enums.CourseSatatus;
import com.okamor.mmbeauty.model.enums.UserStatus;
import com.okamor.mmbeauty.service.ClientService;
import com.okamor.mmbeauty.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 36000, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Controller {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/client/list")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/client/register")
    public ResponseEntity<Void> registerNewClient(@RequestBody Client client) {
        long registered = clientService.newClient(client);
        if (registered != 999) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf((int) registered));
        }
    }

    @GetMapping("/client/login/{email}/{password}")
    public ResponseEntity<Client> loginClient(@PathVariable("email") String email, @PathVariable("password") String password) {
        Client client = clientService.clientLogin(email, password);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.accepted().body(client);
        }
    }

    @GetMapping("/client/reset/{email}")
    public ResponseEntity<Void> resetPassword(@PathVariable("email") String email) {
        boolean reset = clientService.resetPassword(email);
        return reset ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatusCode.valueOf(998));
    }

    @PostMapping("/client/edit")
    public Client editClientInfo(@RequestBody Client client) {
        return clientService.modifyClient(client);
    }

    @GetMapping("/client/info/{email}")
    public Client showClientInfo(@PathVariable("email") String email) {
        return clientService.getClientInfo(email);
    }

    @PostMapping("/client/change_status/{id}/{status}")
    public void changeUserStatus(@PathVariable("id") Long id, @PathVariable("status") UserStatus status) {
        clientService.changeClientStatus(id, status);
    }

    @GetMapping("/course/list")
    public List<Course> getCourseList() {
        return courseService.getAllCourse();
    }

    @GetMapping("/course/info/{id}")
    public ResponseEntity<Course> getCourseInfo(@PathVariable("id") Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.accepted().body(course);
    }

    @PostMapping("/course/change_status/{id}/{status}")
    public void changeCourseStatus(@PathVariable("id") long id, @PathVariable("status")CourseSatatus status) {
        courseService.changeCourseStatus(id, status);
    }

    @PostMapping("/course/add")
    public ResponseEntity<Void> addNewCourse(@RequestBody Course course) {
        long newCourse = courseService.newCourse(course);
        if (newCourse != 997) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf((int) newCourse));
        }
    }

    @PostMapping("/course/edit")
    public Course modifyCourse(Course course) {
        return courseService.editCourse(course);
    }

    @GetMapping("/payButton/{id}")
    public String genPayButton(@PathVariable("id") long id) {
        return null;
    }

}
