const express = require("express");
const { authenticate } = require("../auth/authMiddleware");
const {
  addFavoriteRecipe,
  getFavoriteRecipes,
  removeFavoriteRecipe,
} = require("./favoriteController");

const router = express.Router();

router.post("/", authenticate, addFavoriteRecipe);
router.get("/", authenticate, getFavoriteRecipes); 
router.delete("/:id", authenticate, removeFavoriteRecipe); 

module.exports = router;
