package com.example.lab04_springboot.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.lab04_springboot.model.Student;
import com.example.lab04_springboot.repository.StudentRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Chỉ load data nếu database trống
        if (studentRepository.count() > 0) {
            System.out.println("Database already has data, skipping CSV import.");
            return;
        }

        String csvFile = Paths.get("data", "data.csv").toString();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int count = 0;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                if (values.length >= 4) {
                    Student student = new Student();
                    // CSV format: id, name, email, age
                    // Không set ID vì dùng auto-generate
                    student.setName(values[1].trim());
                    student.setEmail(values[2].trim());
                    student.setAge(Integer.parseInt(values[3].trim()));
                    
                    studentRepository.save(student);
                    count++;
                }
            }
            
            System.out.println("Loaded " + count + " students from CSV file.");
        } catch (Exception e) {
            System.err.println("Error loading CSV file: " + e.getMessage());
        }
    }
}
