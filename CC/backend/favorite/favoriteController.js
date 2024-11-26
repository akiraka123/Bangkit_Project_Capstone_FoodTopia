const admin = require("../firebase");
const db = admin.firestore();
// Tambahkan makanan ke favorit
const addFavoriteRecipe = async (req, res) => {
  try {
    const { RecipeId } = req.body; // Ambil ID resep dari body request
    const email = req.user.email; // Email pengguna dari token

    if (!RecipeId) {
      return res.status(400).json({ message: "RecipeId is required" });
    }

    // Ambil data resep dari koleksi `recipes`
    const recipeDoc = await db.collection("recipes").doc(RecipeId).get();
    if (!recipeDoc.exists) {
      return res.status(404).json({ message: "Recipe not found" });
    }

    const recipeData = recipeDoc.data(); // Data lengkap resep

    // Periksa apakah resep sudah ada di favorit pengguna
    const favoriteRef = db.collection("users").doc(email).collection("favorites").doc(RecipeId);
    const favoriteDoc = await favoriteRef.get();
    if (favoriteDoc.exists) {
      return res.status(400).json({ message: "Recipe already in favorites" });
    }

    // Simpan data resep ke subkoleksi `favorites`
    await favoriteRef.set({
      ...recipeData, // Salin semua data dari koleksi `recipes`
      addedAt: admin.firestore.FieldValue.serverTimestamp(), // Tambahkan timestamp
    });

    res.status(201).json({ message: "Recipe added to favorites", recipe: recipeData });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};


// Ambil daftar favorit pengguna
const getFavoriteRecipes = async (req, res) => {
  try {
    const email = req.user.email; // Dapatkan email pengguna dari token JWT

    // Ambil data dari subkoleksi `favorites`
    const snapshot = await db.collection("users").doc(email).collection("favorites").get();

    if (snapshot.empty) {
      return res.status(200).json({ message: "No favorite recipes found", favorites: [] });
    }

    // Mapping data dari dokumen favorit
    const favorites = snapshot.docs.map((doc) => ({
      id: doc.id,
      ...doc.data(),
    }));

    res.status(200).json({ favorites });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Hapus makanan dari favorit
const removeFavoriteRecipe = async (req, res) => {
  try {
    const email = req.user.email; // Dapatkan email pengguna dari token
    const { id } = req.params; // ID resep favorit yang akan dihapus

    const favoriteRef = db.collection("users").doc(email).collection("favorites").doc(id);

    const favoriteDoc = await favoriteRef.get();
    if (!favoriteDoc.exists) {
      return res.status(404).json({ message: "Favorite recipe not found" });
    }

    await favoriteRef.delete();
    res.status(200).json({ message: "Recipe removed from favorites" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Using Auth Firebase
// const addFavoriteRecipe = async (req, res) => {
//   try {
//     const { RecipeId, Name } = req.body;
//     const uid = req.user.uid; // UID dari token Firebase

//     if (!RecipeId || !Name) {
//       return res.status(400).json({ message: "RecipeId and Name are required" });
//     }

//     const favoriteRef = db.collection("users").doc(uid).collection("favorites").doc(RecipeId);

//     const favoriteDoc = await favoriteRef.get();
//     if (favoriteDoc.exists) {
//       return res.status(400).json({ message: "Recipe already in favorites" });
//     }

//     await favoriteRef.set({
//       RecipeId,
//       Name,
//       addedAt: admin.firestore.FieldValue.serverTimestamp(),
//     });

//     res.status(201).json({ message: "Recipe added to favorites" });
//   } catch (error) {
//     res.status(500).json({ error: error.message });
//   }
// };
// const getFavoriteRecipes = async (req, res) => {
//   try {
//     const uid = req.user.uid; // UID dari token Firebase

//     const snapshot = await db.collection("users").doc(uid).collection("favorites").get();

//     if (snapshot.empty) {
//       return res.status(404).json({ message: "No favorite recipes found" });
//     }

//     const favorites = snapshot.docs.map((doc) => ({ id: doc.id, ...doc.data() }));
//     res.status(200).json(favorites);
//   } catch (error) {
//     res.status(500).json({ error: error.message });
//   }
// };
// const removeFavoriteRecipe = async (req, res) => {
//   try {
//     const uid = req.user.uid; // UID dari token Firebase
//     const { id } = req.params; // ID makanan yang akan dihapus

//     const favoriteRef = db.collection("users").doc(uid).collection("favorites").doc(id);

//     const favoriteDoc = await favoriteRef.get();
//     if (!favoriteDoc.exists) {
//       return res.status(404).json({ message: "Favorite recipe not found" });
//     }

//     await favoriteRef.delete();
//     res.status(200).json({ message: "Recipe removed from favorites" });
//   } catch (error) {
//     res.status(500).json({ error: error.message });
//   }
// };
module.exports = {
  addFavoriteRecipe,
  getFavoriteRecipes,
  removeFavoriteRecipe,
};
