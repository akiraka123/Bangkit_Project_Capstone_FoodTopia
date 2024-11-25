const admin = require("../firebase");
const db = admin.firestore();

const getAllRecipes = async (req, res) => {
  try {
    const snapshot = await db.collection("recipes").get();
    const recipes = snapshot.docs.map((doc) => ({ id: doc.id, ...doc.data() }));
    res.status(200).json(recipes);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getPaginatedRecipes = async (req, res) => {
  try {
    const { page = 1, limit = 10 } = req.query; // Ambil query string page dan limit
    const offset = (page - 1) * limit; // Hitung offset

    const snapshot = await db.collection("recipes")
      .orderBy("RecipeId") // Urutkan berdasarkan ID (pastikan ada index di Firestore)
      .offset(offset)      // Lewati dokumen sebelumnya
      .limit(Number(limit))// Ambil dokumen sesuai limit
      .get();

    const recipes = snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));

    res.status(200).json({
      page: Number(page),
      limit: Number(limit),
      total: recipes.length,
      recipes
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getRecipeById = async (req, res) => {
  try {
    const { id } = req.params;
    const doc = await db.collection("recipes").doc(id).get();

    if (!doc.exists) {
      return res.status(404).json({ message: "Recipe not found" });
    }

    res.status(200).json({ id: doc.id, ...doc.data() });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const addRecipe = async (req, res) => {
  try {
    const newRecipe = req.body;
    const docRef = await db.collection("recipes").add(newRecipe);
    res.status(201).json({ id: docRef.id, message: "Recipe added successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const updateRecipe = async (req, res) => {
  try {
    const { id } = req.params;
    const updatedData = req.body;

    await db.collection("recipes").doc(id).update(updatedData);

    res.status(200).json({ message: "Recipe updated successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const deleteRecipe = async (req, res) => {
  try {
    const { id } = req.params;

    await db.collection("recipes").doc(id).delete();

    res.status(200).json({ message: "Recipe deleted successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const searchRecipes = async (req, res) => {
  try {
    const { query, limit = 10, page = 1 } = req.query;

    if (!query) {
      return res.status(400).json({ message: "Query parameter is required for search" });
    }

    const offset = (page - 1) * limit;

    // Search di Firestore berdasarkan "Name" dan "Keywords"
    const snapshot = await db
      .collection("recipes")
      .where("Name", ">=", query)
      .where("Name", "<=", query + "\uf8ff")
      .orderBy("Name")
      .offset(offset)
      .limit(Number(limit))
      .get();

    const recipes = snapshot.docs.map((doc) => ({ id: doc.id, ...doc.data() }));

    res.status(200).json({
      query,
      page: Number(page),
      limit: Number(limit),
      total: recipes.length,
      recipes,
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getRandomRecipes = async (req, res) => {
  try {
    const { count = 5 } = req.query; // Jumlah random makanan, default 10
    const maxId = 500000; // Angka maksimum dalam rentang ID

    const randomRecipes = new Set(); // Gunakan Set untuk menghindari duplikasi
    const results = [];

    while (randomRecipes.size < count) {
      const randomId = Math.floor(Math.random() * maxId) + 1; // Pilih angka acak
      const docId = `recipe_${randomId}`;

      if (!randomRecipes.has(docId)) {
        const doc = await db.collection("recipes").doc(docId).get();
        if (doc.exists) {
          randomRecipes.add(docId);
          results.push({ id: doc.id, ...doc.data() });
        }
      }
    }

    res.status(200).json({
      total: results.length,
      recipes: results,
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};


module.exports = { getPaginatedRecipes,getAllRecipes, getRecipeById, addRecipe, updateRecipe, deleteRecipe,searchRecipes, getRandomRecipes };
