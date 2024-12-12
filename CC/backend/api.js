require("dotenv").config();
const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");

// Import routes
const authRoutes = require("./auth/authRoutes");
const recipesRoutes = require("./recipes/recipesRoutes");
const favoriteRoutes = require("./favorite/favoriteRoutes"); 

// Inisialisasi server
const app = express();
app.use(cors());
app.use(bodyParser.json());

// // Endpoint untuk root
// app.get("/", (req, res) => {
//   res.send({
//     message: "Welcome to the API!",
//     routes: {
//       auth: "/auth",
//       recipes: "/recipes",
//     },
//   });
// });
// Rute utama
app.use("/api/auth", authRoutes);
app.use("/api/recipes", recipesRoutes);
app.use("/api/favorite", favoriteRoutes);

// Jalankan server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server berjalan di http://localhost:${PORT}`);
});
