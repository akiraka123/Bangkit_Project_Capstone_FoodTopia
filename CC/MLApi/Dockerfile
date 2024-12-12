# Gunakan image Python
FROM python:3.11

# Set working directory
WORKDIR /app

# Copy semua file ke dalam container
COPY . /app

# Install dependensi
RUN pip install --no-cache-dir -r requirements.txt

# Expose port 8080
EXPOSE 8080

# Jalankan aplikasi
CMD ["waitress-serve", "--port=8080", "app:app"]
