# Gunakan base image Node.js
FROM node:16

# Tentukan direktori kerja di dalam container
WORKDIR /usr/src/app

# Salin package.json dan package-lock.json
COPY package*.json ./

# Install dependensi
RUN npm install

# Salin semua file proyek ke dalam container
COPY . .

# Expose port yang digunakan aplikasi
EXPOSE 3000

# Tentukan perintah untuk menjalankan server
CMD ["npm", "start"]
