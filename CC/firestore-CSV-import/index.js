const fs = require("fs");
const csv = require("csv-parser");

const admin = require("firebase-admin");
const serviceAccount = require("./firebase-key.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

const db = admin.firestore();

// Fungsi untuk membersihkan dan memproses string menjadi array untuk Images
const cleanImages = (str) => {
  if (!str) return []; // Jika string kosong, kembalikan array kosong

  // Hapus kutipan tunggal di awal/akhir dan simbol penutup array ' atau ']
  str = str.replace(/(^'|'\]$)/g, ""); 

  // Pisah berdasarkan URL dengan pola regex untuk menangkap URL yang valid
  return str
    .split(/(https:\/\/[^\s,]+)/g) // Pecah berdasarkan pola URL
    .filter((url) => url.startsWith("https")); // Ambil hanya elemen yang valid sebagai URL
};

// Fungsi untuk membersihkan string JSON umum
const cleanString = (str) => {
  if (!str) return "[]"; // Jika string kosong atau undefined, kembalikan array kosong
  return str
    .replace(/""/g, '\\"') // Escape double quotes
    .replace(/"([^"]*)"/g, (match) => match.replace(/"/g, '\\"')) // Escape kutipan dalam teks
    .replace(/'/g, '"'); // Ubah kutipan tunggal menjadi ganda
};

// Fungsi untuk mengimpor data dari CSV
async function importCSV(filePath) {
  try {
    const recipes = [];

    // Membaca file CSV
    fs.createReadStream(filePath)
      .pipe(csv())
      .on("data", (row) => {
        try {
          // Parsing data baris
          const recipe = {
            RecipeId: parseInt(row.RecipeId, 10),
            Name: row.Name,
            Images: cleanImages(row.Images), // Gunakan fungsi cleanImages
            RecipeCategory: row.RecipeCategory,
            Keywords: JSON.parse(cleanString(row.Keywords)),
            RecipeIngredientQuantities: JSON.parse(cleanString(row.RecipeIngredientQuantities)),
            RecipeIngredientParts: JSON.parse(cleanString(row.RecipeIngredientParts)),
            Calories: parseFloat(row.Calories) || 0,
            FatContent: parseFloat(row.FatContent) || 0,
            SaturatedFatContent: parseFloat(row.SaturatedFatContent) || 0,
            CholesterolContent: parseFloat(row.CholesterolContent) || 0,
            SodiumContent: parseFloat(row.SodiumContent) || 0,
            CarbohydrateContent: parseFloat(row.CarbohydrateContent) || 0,
            FiberContent: parseFloat(row.FiberContent) || 0,
            SugarContent: parseFloat(row.SugarContent) || 0,
            ProteinContent: parseFloat(row.ProteinContent) || 0,
            RecipeServings: parseFloat(row.RecipeServings) || 0,
            RecipeInstructions: JSON.parse(cleanString(row.RecipeInstructions)),
          };
          recipes.push(recipe);
        } catch (err) {
          console.error("Error parsing row:", row, err.message);
        }
      })
      .on("end", async () => {
        console.log("CSV file successfully processed.");

        // Mengimpor data ke Firestore
        for (const recipe of recipes) {
          const docId = `recipe_${recipe.RecipeId}`;
          try {
            await db.collection("recipes").doc(docId).set(recipe);
            console.log(`Data untuk RecipeId ${recipe.RecipeId} berhasil ditambahkan.`);
          } catch (error) {
            console.error(`Gagal menambahkan data RecipeId ${recipe.RecipeId}:`, error.message);
          }
        }
        console.log("Semua data berhasil diimpor ke Firestore!");
      });
  } catch (error) {
    console.error("Terjadi kesalahan saat mengimpor data:", error.message);
  }
}

// Jalankan fungsi impor
importCSV("./recipes.csv"); // Ganti dengan path file CSV Anda
