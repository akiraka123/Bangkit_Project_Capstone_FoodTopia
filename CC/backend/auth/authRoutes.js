const express = require("express");
const {
  register,
  login,
  googleLogin,
  forgotPassword,
  resetPassword,
  getUserName,
} = require("./authController");
const { authenticate } = require("./authMiddleware");

const router = express.Router();

router.post("/register", register);
router.post("/login", login);
router.post("/google", googleLogin);
router.post("/forgot-password", forgotPassword);
router.post("/reset-password", resetPassword);
router.get("/user", authenticate, getUserName);
module.exports = router;
