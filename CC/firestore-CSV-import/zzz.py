import pandas as pd

# Baca file CSV
file_path = "tes.csv"  # Ganti dengan path file Anda
df = pd.read_csv(file_path)

# Hapus baris dengan kolom kosong
df_cleaned = df.dropna()

# Simpan hasil ke file baru
output_path = "recipes.csv"  # Ganti dengan nama file yang Anda inginkan
df_cleaned.to_csv(output_path, index=False)

print(f"File bersih telah disimpan ke: {output_path}")