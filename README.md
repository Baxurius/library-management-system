# 📚 Library Management System (Spring Boot)

Web aplikacija za upravljanje bibliotekom, razvijena u Java Spring Boot-u. Omogućava studentima da pregledaju i iznajmljuju knjige, dok administratori mogu da upravljaju korisnicima, knjigama i pozajmicama.

---

## ✨ Funkcionalnosti

### 👨‍🎓 STUDENT
- Registracija i login
- Pregled svih knjiga
- Iznajmljivanje i vraćanje knjiga
- Pregled sopstvenih pozajmica

### 👮 ADMIN
- Sve što može STUDENT
- Dodavanje i brisanje knjiga
- Pregled svih korisnika
- Brisanje korisnika
- Pregled svih pozajmica

---

## 🔐 Uloge i prava

| Funkcija | STUDENT | ADMIN |
|---|---:|---:|
| Prijava / Registracija | ✅ | ✅ |
| Pregled knjiga | ✅ | ✅ |
| Dodavanje / Brisanje knjiga | ❌ | ✅ |
| Moje pozajmice | ✅ | ✅ |
| Sve pozajmice | ❌ | ✅ |
| Upravljanje korisnicima | ❌ | ✅ |

---

## 🛠️ Tehnologije

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **Thymeleaf**
- **Maven**

---

## 🧩 Struktura projekta

```
src/
  main/
    java/com/example/library/
      controller/
      model/
      repository/
      service/
      security/
    resources/
      templates/        # Thymeleaf HTML fajlovi
      static/css/       # CSS
      application.properties
```

### 📦 Model (Entiteti)
- **User** (username, password, role)
- **Book** (title, isbn, author, genre)
- **Loan** (user, book, loanDate, returnDate)
- **Author**
- **Genre**

### 🗃️ Repository
JPA repozitorijumi:
- `UserRepository`
- `BookRepository`
- `LoanRepository`
- `AuthorRepository`
- `GenreRepository`

### ⚙️ Service
Poslovna logika:
- `UserService`
- `BookService`
- `LoanService`
- `AuthorService`
- `GenreService`

### 🎮 Controller
REST i MVC kontroleri:
- `AuthController`
- `BookController`, `BookViewController`
- `UserController`, `UserViewController`
- `LoanController`
- `AuthorController`, `GenreController`

### 🔒 Security
- `CustomUserDetails` i `CustomUserDetailsService`
- `SecurityConfig` definiše prava pristupa po URL-u

### 🖥️ Templates (Thymeleaf)
- `login.html`
- `register.html`
- `books.html`
- `book-form.html`
- `my-loans.html`
- `loans.html`
- `users.html`

---

## 🔑 Autentifikacija i autorizacija

- Prilikom registracije, lozinka se **bcrypt-uje** i dodeljuje se rola **STUDENT**.
- Spring Security koristi `CustomUserDetailsService` za pronalaženje korisnika i proveru uloge.
- Nakon uspešne prijave, korisnik se preusmerava na:  
  **`/books-view`**

---

## 🧾 Prikaz admin opcija u UI

U Thymeleaf-u se admin sadržaj prikazuje uslovno:

```html
<th:block th:if="${isAdmin}">
    <!-- vidi samo admin -->
</th:block>
```

U kontroleru se dodeljuje `isAdmin`:

```java
model.addAttribute("isAdmin", currentUser.hasRole("ADMIN"));
```

---

## 👤 Test korisnici

| Username | Password | Role |
|---|---|---|
| admin | admin | ADMIN |
| Zika | 123 | STUDENT |

> Ako u tvojoj bazi ne postoje test korisnici, možeš ih napraviti ručno ili preko registracije, pa adminu promeniti rolu u bazi.

---

## ▶️ Pokretanje aplikacije

### 1) Podesi bazu (MySQL)
Kreiraj bazu:

```sql
CREATE DATABASE library_db;
```

### 2) Podesi `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=
```

> Ako imaš lozinku na MySQL root nalogu, upiši je u `spring.datasource.password`.

### 3) Startuj aplikaciju

```bash
./mvnw spring-boot:run
```

Otvori u browser-u:

- `http://localhost:8080/login`

---

## 🚀 Potencijalna unapređenja

- Dodavanje Bootstrap-a za lepši UI
- Obaveštenja o isteku pozajmica
- Pretraga i paginacija knjiga
- Email notifikacije
