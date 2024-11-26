const express = require("express");
const { authenticate } = require("../auth/authMiddleware");
const {
  addFavoriteRecipe,
  getFavoriteRecipes,
  removeFavoriteRecipe,
} = require("./favoriteController");

const router = express.Router();

router.post("/", authenticate, addFavoriteRecipe); // Tambahkan makanan ke favorit
router.get("/", authenticate, getFavoriteRecipes); // Ambil daftar favorit
router.delete("/:id", authenticate, removeFavoriteRecipe); // Hapus makanan dari favorit

module.exports = router;
