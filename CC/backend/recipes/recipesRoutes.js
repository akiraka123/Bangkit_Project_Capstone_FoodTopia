const express = require("express");
const { authenticate } = require("../auth/authMiddleware");
const {
  getAllRecipes,
  getRecipeById,
  addRecipe,
  updateRecipe,
  deleteRecipe,
  getPaginatedRecipes,
  searchRecipes,
  getRandomRecipes,
} = require("./recipesController");

const router = express.Router();

router.get("/", getAllRecipes);
router.get("/search", searchRecipes);
router.get("/random", getRandomRecipes);
router.get("/paginated", getPaginatedRecipes);
router.get("/:id",  getRecipeById);
router.post("/", authenticate, addRecipe);
router.put("/:id", authenticate, updateRecipe);
router.delete("/:id", authenticate, deleteRecipe);

module.exports = router;
