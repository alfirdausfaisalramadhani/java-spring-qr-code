package com.example.springqr.controller;

import com.example.springqr.model.Student;
import com.example.springqr.service.StudentService;
import com.example.springqr.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController   {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get-student")
    public ResponseEntity<List<Student>> getStudent() throws IOException, WriterException {
        List<Student> students = studentService.getStudents();
        if (!students.isEmpty()){
            for (Student student : students){
                QRCodeGenerator.generateQRCode(student);
            }
        }
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/find-by-id")
    public Student findById(@RequestParam Long id){
        return studentService.findById(id);
    }
}
