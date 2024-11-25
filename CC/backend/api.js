require("dotenv").config();
const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");

// Import routes
const authRoutes = require("./auth/authRoutes");
const recipesRoutes = require("./recipes/recipesRoutes");

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
app.use("/auth", authRoutes);
app.use("/recipes", recipesRoutes);

// Jalankan server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server berjalan di http://localhost:${PORT}`);
});
