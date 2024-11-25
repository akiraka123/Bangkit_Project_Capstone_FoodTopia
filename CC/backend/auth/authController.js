const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const admin = require("../firebase");

const db = admin.firestore();
const SECRET_KEY = process.env.SECRET_KEY;
const RESET_PASSWORD_EXPIRATION = parseInt(process.env.RESET_PASSWORD_EXPIRATION, 10);

const register = async (req, res) => {
  try {
    const { email, password } = req.body;

    const hashedPassword = await bcrypt.hash(password, 10);
    await db.collection("users").doc(email).set({ email, password: hashedPassword });

    res.status(201).json({ message: "User registered successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const login = async (req, res) => {
  try {
    const { email, password } = req.body;

    const userDoc = await db.collection("users").doc(email).get();
    if (!userDoc.exists) {
      return res.status(404).json({ message: "User not found" });
    }

    const userData = userDoc.data();
    const isPasswordValid = await bcrypt.compare(password, userData.password);
    if (!isPasswordValid) {
      return res.status(401).json({ message: "Invalid password" });
    }

    const token = jwt.sign({ email }, SECRET_KEY, { expiresIn: "1h" });
    res.status(200).json({ token });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const googleLogin = async (req, res) => {
  try {
    const { idToken } = req.body;

    const decodedToken = await admin.auth().verifyIdToken(idToken);
    const { email } = decodedToken;

    const userDoc = await db.collection("users").doc(email).get();
    if (!userDoc.exists) {
      await db.collection("users").doc(email).set({ email, googleAuth: true });
    }

    const token = jwt.sign({ email }, SECRET_KEY, { expiresIn: "1h" });
    res.status(200).json({ token });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const forgotPassword = async (req, res) => {
  try {
    const { email } = req.body;

    const userDoc = await db.collection("users").doc(email).get();
    if (!userDoc.exists) {
      return res.status(404).json({ message: "User not found" });
    }

    const resetToken = jwt.sign({ email }, SECRET_KEY, { expiresIn: RESET_PASSWORD_EXPIRATION });
    res.status(200).json({ resetToken, message: "Reset password token generated." });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const resetPassword = async (req, res) => {
  try {
    const { resetToken, newPassword } = req.body;

    const decoded = jwt.verify(resetToken, SECRET_KEY);
    const { email } = decoded;

    const hashedPassword = await bcrypt.hash(newPassword, 10);
    await db.collection("users").doc(email).update({ password: hashedPassword });

    res.status(200).json({ message: "Password reset successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

module.exports = { register, login, googleLogin, forgotPassword, resetPassword };
