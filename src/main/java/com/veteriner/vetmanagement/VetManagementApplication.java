package com.veteriner.vetmanagement;

import com.veteriner.vetmanagement.entity.*;
import com.veteriner.vetmanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class VetManagementApplication {

    private final DoctorService doctorService;
    private final AvailableDateService availableDateService;
    private final CustomerService customerService;
    private final AnimalService animalService;
    private final VaccineService vaccineService;
    private final AppointmentService appointmentService;

    public static void main(String[] args) {
        SpringApplication.run(VetManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            while (true) {
                System.out.println("\n--- VETERİNER YÖNETİM SİSTEMİ MENÜ ---");
                System.out.println("1. Doktor Ekle");
                System.out.println("2. Doktor Müsait Günü Ekle");
                System.out.println("3. Müşteri Ekle");
                System.out.println("4. Hayvan Ekle");
                System.out.println("5. Aşı Ekle");
                System.out.println("6. Randevu Oluştur");
                System.out.println("7. Tüm Randevuları Listele");
                System.out.println("8. Çıkış");
                System.out.print("Seçiminiz: ");

                int secim = Integer.parseInt(scanner.nextLine());

                try {
                    switch (secim) {
                        case 1:
                            System.out.print("Ad: ");
                            String dName = scanner.nextLine();
                            System.out.print("Telefon: ");
                            String dPhone = scanner.nextLine();
                            System.out.print("Mail: ");
                            String dMail = scanner.nextLine();
                            System.out.print("Adres: ");
                            String dAddress = scanner.nextLine();
                            System.out.print("Şehir: ");
                            String dCity = scanner.nextLine();

                            Doctor doctor = Doctor.builder()
                                    .name(dName).phone(dPhone).mail(dMail)
                                    .address(dAddress).city(dCity)
                                    .build();
                            doctorService.createDoctor(doctor);
                            System.out.println("Doktor eklendi.");
                            break;

                        case 2:
                            System.out.print("Doktor ID: ");
                            Long docId = Long.parseLong(scanner.nextLine());
                            System.out.print("Müsait Gün (yyyy-MM-dd): ");
                            LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);
                            AvailableDate availableDate = AvailableDate.builder()
                                    .availableDate(date)
                                    .doctor(doctorService.getDoctor(docId))
                                    .build();
                            availableDateService.createAvailableDate(availableDate);
                            System.out.println("Müsait gün eklendi.");
                            break;

                        case 3:
                            System.out.print("Ad: ");
                            String cName = scanner.nextLine();
                            System.out.print("Telefon: ");
                            String cPhone = scanner.nextLine();
                            System.out.print("Mail: ");
                            String cMail = scanner.nextLine();
                            System.out.print("Adres: ");
                            String cAddress = scanner.nextLine();
                            System.out.print("Şehir: ");
                            String cCity = scanner.nextLine();

                            Customer customer = Customer.builder()
                                    .name(cName).phone(cPhone).mail(cMail)
                                    .address(cAddress).city(cCity)
                                    .build();
                            customerService.createCustomer(customer);
                            System.out.println("Müşteri eklendi.");
                            break;

                        case 4:
                            System.out.print("Ad: ");
                            String aName = scanner.nextLine();
                            System.out.print("Tür: ");
                            String species = scanner.nextLine();
                            System.out.print("Irk: ");
                            String breed = scanner.nextLine();
                            System.out.print("Cinsiyet: ");
                            String gender = scanner.nextLine();
                            System.out.print("Renk: ");
                            String colour = scanner.nextLine();
                            System.out.print("Doğum Tarihi (yyyy-MM-dd): ");
                            LocalDate dob = LocalDate.parse(scanner.nextLine(), dateFormatter);
                            System.out.print("Müşteri ID: ");
                            Long custId = Long.parseLong(scanner.nextLine());

                            Animal animal = Animal.builder()
                                    .name(aName).species(species).breed(breed)
                                    .gender(gender).colour(colour).dateOfBirth(dob)
                                    .customer(customerService.getCustomer(custId))
                                    .build();
                            animalService.createAnimal(animal);
                            System.out.println("Hayvan eklendi.");
                            break;

                        case 5:
                            System.out.print("Hayvan ID: ");
                            Long animalId = Long.parseLong(scanner.nextLine());
                            System.out.print("Aşı Adı: ");
                            String vName = scanner.nextLine();
                            System.out.print("Aşı Kodu: ");
                            String vCode = scanner.nextLine();
                            System.out.print("Başlangıç Tarihi (yyyy-MM-dd): ");
                            LocalDate start = LocalDate.parse(scanner.nextLine(), dateFormatter);
                            System.out.print("Bitiş Tarihi (yyyy-MM-dd): ");
                            LocalDate end = LocalDate.parse(scanner.nextLine(), dateFormatter);

                            Vaccine vaccine = Vaccine.builder()
                                    .name(vName).code(vCode)
                                    .protectionStartDate(start)
                                    .protectionFinishDate(end)
                                    .animal(animalService.getAnimal(animalId))
                                    .build();
                            vaccineService.createVaccine(vaccine);
                            System.out.println("Aşı eklendi.");
                            break;

                        case 6:
                            System.out.print("Doktor ID: ");
                            Long rDocId = Long.parseLong(scanner.nextLine());
                            System.out.print("Hayvan ID: ");
                            Long rAnimalId = Long.parseLong(scanner.nextLine());
                            System.out.print("Randevu Tarihi-Saati (yyyy-MM-dd HH:mm): ");
                            LocalDateTime rDateTime = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);

                            Appointment appointment = Appointment.builder()
                                    .appointmentDate(rDateTime)
                                    .doctor(doctorService.getDoctor(rDocId))
                                    .animal(animalService.getAnimal(rAnimalId))
                                    .build();
                            appointmentService.createAppointment(appointment);
                            System.out.println("Randevu eklendi.");
                            break;

                        case 7:
                            List<Appointment> appointments = appointmentService.getAllAppointments();
                            appointments.forEach(a ->
                                    System.out.println(a.getId() + " | " + a.getAppointmentDate()
                                            + " | Doktor: " + a.getDoctor().getName()
                                            + " | Hayvan: " + a.getAnimal().getName()));
                            break;

                        case 8:
                            System.out.println("Çıkılıyor...");
                            System.exit(0);
                            break;

                        default:
                            System.out.println("Geçersiz seçim!");
                    }

                } catch (Exception e) {
                    System.out.println("Hata: " + e.getMessage());
                }
            }
        };
    }
}
