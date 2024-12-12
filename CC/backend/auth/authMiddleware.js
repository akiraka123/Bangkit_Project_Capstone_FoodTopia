const jwt = require("jsonwebtoken");
const SECRET_KEY = process.env.SECRET_KEY;

const authenticate = (req, res, next) => {
  const authHeader = req.headers.authorization;

  if (!authHeader || !authHeader.startsWith("Bearer ")) {
    return res.status(401).json({ message: "Authorization token required" });
  }

  const token = authHeader.split(" ")[1];

  try {
    const decoded = jwt.verify(token, SECRET_KEY);
    req.user = decoded; // Menyimpan data pengguna di request
    next();
  } catch (error) {
    return res.status(401).json({ message: "Invalid or expired token" });
  }
};

module.exports = { authenticate }

// middleware using Auth firebase
// const { admin } = require("../firebase");

// const authenticate = async (req, res, next) => {
//   const authHeader = req.headers.authorization;

//   if (!authHeader || !authHeader.startsWith("Bearer ")) {
//     return res.status(401).json({ message: "Authorization token required" });
//   }

//   const idToken = authHeader.split(" ")[1];

//   try {
//     const decodedToken = await admin.auth().verifyIdToken(idToken);
//     req.user = {
//       uid: decodedToken.uid, // UID pengguna dari Firebase
//       email: decodedToken.email, // Email pengguna
//     };
//     next();
//   } catch (error) {
//     return res.status(401).json({ message: "Invalid or expired token", error: error.message });
//   }
// };

// module.exports = { authenticate };
