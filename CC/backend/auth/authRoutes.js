const express = require("express");
const {
  register,
  login,
  googleLogin,
  forgotPassword,
  resetPassword,
} = require("./authController");

const router = express.Router();

router.post("/register", register);
router.post("/login", login);
router.post("/google", googleLogin);
router.post("/forgot-password", forgotPassword);
router.post("/reset-password", resetPassword);

module.exports = router;
