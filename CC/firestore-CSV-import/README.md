# Import file csv ke firestore

This is a project to import csv file to firestore with firebase_service key in the form of JSON file. Follow the steps below to run the project.
---

## 📋 Prerequisites
Download file recipes.csv di https://drive.google.com/file/d/1VI3l83kwqCq7MfktjkC9PbJ85Jv7Ddod/view?usp=drive_link 



## 🚀 Cara Menjalankan Proyek

### Clone Repository
Clone repository :
  ```bash
  git clone --branch CC https://github.com/akiraka123/Bangkit_Project_Capstone_FoodTopia.git
  cd firestore-CSV-import
  ```
### Install Dependencies
  ```bash
    npm install
  ```
### Inisialisasi Firestore Service Key
1. Prepare your Firestore service key JSON file. This file can be obtained from [Google Cloud Console](https://console.cloud.google.com/).
2. Put the JSON file into your project directory. Example: `firebase-key.json`.
3. Change the Firestore initialization code to read your JSON file like this:

  ```javascript
   const admin = require('firebase-admin');
   const serviceAccount = require('./firebase-key.json'); // Replace with the name and location of your JSON file

   admin.initializeApp({
       credential: admin.credential.cert(serviceAccount),
   });
  
   const db = admin.firestore();
  ```
### Jalankan index.js

  ```bash
    node index.js
  ```
