package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import model.Student;
import repository.StudentRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DataLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Chỉ load dữ liệu nếu database rỗng
        if (studentRepository.count() == 0) {
            loadDataFromCSV();
        }
    }

    private void loadDataFromCSV() {
        String csvFile = "data/data.csv";
        String line;
        String separator = ",";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Bỏ qua dòng header
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                
                if (data.length >= 4) {
                    Student student = new Student();
                    // Không set id vì sẽ được tự động generate
                    student.setName(data[1].trim());
                    student.setEmail(data[2].trim());
                    student.setAge(Integer.parseInt(data[3].trim()));
                    
                    studentRepository.save(student);
                }
            }
            System.out.println("Đã load dữ liệu từ data.csv vào database!");
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Lỗi khi parse dữ liệu: " + e.getMessage());
        }
    }
}
