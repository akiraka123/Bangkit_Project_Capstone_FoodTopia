import csv
import ast  # Untuk parsing string array menjadi array Python
import firebase_admin
from firebase_admin import credentials, firestore

# Inisialisasi Firestore
cred = credentials.Certificate('firebase-key.json')  # Ganti dengan file JSON Service Account Anda
firebase_admin.initialize_app(cred)
db = firestore.client()

# Nama koleksi di Firestore
collection_name = "recipes"  # Ganti dengan nama koleksi yang Anda inginkan

# Baca file CSV, tambahkan ID dan NameLower, lalu unggah ke Firestore
file_path = "recipes.csv"  # Ganti dengan path file CSV Anda
with open(file_path, mode='r', encoding='utf-8') as file:
    csv_reader = csv.DictReader(file)
    data = list(csv_reader)  # Convert ke list untuk memproses lebih lanjut

    for idx, row in enumerate(data):
        try:
            # Tambahkan kolom ID dengan nilai berurutan
            row['ID'] = idx

            # Tambahkan kolom NameLower dengan nilai nama dalam huruf kecil
            row['NameLower'] = row['Name'].lower()

            # Konversi string array ke array Python
            for key in ['Images', 'Keywords', 'RecipeIngredientQuantities', 'RecipeIngredientParts', 'RecipeInstructions']:
                if row[key]:  # Cek jika kolom tidak kosong
                    row[key] = ast.literal_eval(row[key])
            
            # Tambahkan data ke Firestore
            doc_id = str(row['ID'])  # Gunakan kolom ID baru sebagai ID dokumen
            if db.collection(collection_name).document(doc_id).get().exists:
              print(f"Dokumen dengan ID {doc_id} sudah ada, lewati.")# Cetak pesan sukses
              continue  # Lewati pengunggahan dokumen ini jika sudah ada
            db.collection(collection_name).document(doc_id).set(row)

            # Cetak pesan sukses
            print(f"Data berhasil diimpor untuk ID: {row['ID']}")

        except Exception as e:
            # Cetak pesan error jika ada masalah
            print(f"Gagal mengimpor data untuk ID: {idx}. Error: {e}")

print("Proses impor selesai!")
