# Import file csv ke firestore

Ini adalah proyek untuk menginisialisasi Firestore dengan kunci layanan berbentuk file JSON. Ikuti langkah-langkah di bawah ini untuk menjalankan proyek.

---

## 📋 Prerequisites

Pastikan Anda telah menginstal:
- [Node.js](https://nodejs.org/) (minimal versi 14)
- NPM (termasuk dalam Node.js)
- Download file recipes.csv di https://drive.google.com/file/d/1VI3l83kwqCq7MfktjkC9PbJ85Jv7Ddod/view?usp=drive_link 



## 🚀 Cara Menjalankan Proyek

### Clone Repository
Clone repository ke mesin lokal Anda:
  ```bash
  git clone --branch CC https://github.com/akiraka123/Bangkit_Project_Capstone_FoodTopia.git
  cd firestore-CSV-import
  ```
### Install De
npm install

###Inisialisasi Firestore Service Key
1. Siapkan file JSON kunci layanan Firestore Anda. File ini dapat diperoleh dari[Google Cloud Console](https://console.cloud.google.com/).
2. Masukkan file JSON tersebut ke dalam direktori proyek Anda. Contoh: `firebase-key.json`.
3. Ubah kode inisialisasi Firestore untuk membaca file JSON Anda seperti ini:

   ```javascript
   const admin = require('firebase-admin');
   const serviceAccount = require('./firebase-key.json'); // Ganti dengan nama dan lokasi file JSON Anda

   admin.initializeApp({
       credential: admin.credential.cert(serviceAccount),
   });
  
   const db = admin.firestore();
  ```
### Jalankan index.js
node index.js
