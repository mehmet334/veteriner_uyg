-- CUSTOMERS
INSERT INTO customers (id, name, phone, mail, address, city) VALUES
(1, 'Ahmet Yılmaz', '5551112233', 'ahmet@mail.com', 'Adres 1', 'İstanbul'),
(2, 'Ayşe Kaya', '5552223344', 'ayse@mail.com', 'Adres 2', 'Ankara'),
(3, 'Mehmet Demir', '5553334455', 'mehmet@mail.com', 'Adres 3', 'İzmir'),
(4, 'Zeynep Şahin', '5554445566', 'zeynep@mail.com', 'Adres 4', 'Bursa'),
(5, 'Ali Can', '5555556677', 'ali@mail.com', 'Adres 5', 'Antalya');

-- DOCTORS
INSERT INTO doctors (id, name, phone, mail, address, city) VALUES
(1, 'Dr. Selim Aksoy', '5551234567', 'selim@vet.com', 'Vet Adres 1', 'İstanbul'),
(2, 'Dr. Elif Güneş', '5552345678', 'elif@vet.com', 'Vet Adres 2', 'Ankara'),
(3, 'Dr. Can Kurt', '5553456789', 'can@vet.com', 'Vet Adres 3', 'İzmir'),
(4, 'Dr. Derya Yıldız', '5554567890', 'derya@vet.com', 'Vet Adres 4', 'Bursa'),
(5, 'Dr. Fatih Öz', '5555678901', 'fatih@vet.com', 'Vet Adres 5', 'Antalya');

-- ANIMALS
INSERT INTO animals (id, name, species, breed, gender, colour, date_of_birth, customer_id) VALUES
(1, 'Karabas', 'Köpek', 'Golden', 'Erkek', 'Sarı', '2018-05-20', 1),
(2, 'Pamuk', 'Kedi', 'Van Kedisi', 'Dişi', 'Beyaz', '2019-07-10', 2),
(3, 'Boncuk', 'Köpek', 'Poodle', 'Erkek', 'Kahverengi', '2020-02-15', 3),
(4, 'Minik', 'Kedi', 'Tekir', 'Dişi', 'Gri', '2021-03-05', 4),
(5, 'Tarçın', 'Köpek', 'Beagle', 'Erkek', 'Kahverengi-Beyaz', '2017-11-11', 5);

-- AVAILABLE_DATES
INSERT INTO available_dates (id, available_date, doctor_id) VALUES
(1, '2024-07-01', 1),
(2, '2024-07-02', 1),
(3, '2024-07-01', 2),
(4, '2024-07-03', 3),
(5, '2024-07-04', 4);

-- VACCINES
INSERT INTO vaccines (id, name, code, protection_start_date, protection_finish_date, animal_id) VALUES
(1, 'Kuduz', 'K001', '2023-01-01', '2024-01-01', 1),
(2, 'Karma', 'K002', '2023-05-01', '2024-05-01', 2),
(3, 'Parvo', 'K003', '2023-07-01', '2024-07-01', 3),
(4, 'Kuduz', 'K001', '2022-06-01', '2023-06-01', 4),
(5, 'Karma', 'K002', '2022-09-01', '2023-09-01', 5);

-- APPOINTMENTS
INSERT INTO appointments (id, appointment_date, doctor_id, animal_id) VALUES
(1, '2024-07-01 10:00:00', 1, 1),
(2, '2024-07-01 11:00:00', 1, 2),
(3, '2024-07-01 12:00:00', 2, 3),
(4, '2024-07-03 10:00:00', 3, 4),
(5, '2024-07-04 09:00:00', 4, 5);
