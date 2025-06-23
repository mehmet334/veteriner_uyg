# 🐾 Veterinary Management System

Veteriner kliniklerinin **müşteri**, **hayvan**, **doktor**, **randevu**, **aşı** ve **uygun tarih** işlemlerini yönetmek için geliştirilmiş bir RESTful API.  
Modern teknolojiyle, esnek, hızlı ve kolay entegre edilebilir.

---

## 🔑 Kısaca Neler Sunar?
✅ Müşteri & hayvan kaydı  
✅ Doktor ve randevu yönetimi  
✅ Aşı geçmişi ve uygun gün takibi  
✅ API odaklı yapı, Postman koleksiyonu desteği  
✅ PostgreSQL ile güçlü veri yönetimi  

---

## ⚙️ Teknolojiler

- **Java 21**
- **Spring Boot 3.5.0**
- **Maven**
- **PostgreSQL 17.3**
- **MapStruct + Lombok**
- **Postman**

---

## 🚀 Hızlı Başlangıç

### Kurulum
```bash
git clone https://github.com/mehmetk-dev/vet-management-system.git
cd vet-management-system
mvn clean install
```

### Çalıştır
```bash
mvn spring-boot:run
```
➡ Uygulama `http://localhost:8080` adresinde aktif.

### Veritabanı
```bash
createdb -U postgres VeterinaryBase
psql -U postgres -d VeterinaryBase -f ./veterinary_database.sql
```
> 🔑 `application.properties` dosyasını bağlantınıza göre düzenleyin.

---

## 🌐 API Örnekleri

| İşlem | Yöntem | URL |
|--------|--------|------|
| Yeni müşteri ekle | POST | `/v1/customers` |
| Hayvanları listele | GET | `/v1/animals` |
| Randevu oluştur | POST | `/v1/appointments` |
| Aşıları getir | GET | `/v1/animals/{id}/vaccines` |
| Doktor sil | DELETE | `/v1/doctors/{id}` |

> Tüm uç noktalar: Postman koleksiyonunda hazır.

---

## ✨ Katılım
Her katkı değerlidir. Fork'la → Geliştir → Pull request gönder.

---

## 📝 Lisans
MIT Lisansı.
![image](https://github.com/user-attachments/assets/b9b2e69b-2ae5-47cf-bee1-555c4e1284ed)
