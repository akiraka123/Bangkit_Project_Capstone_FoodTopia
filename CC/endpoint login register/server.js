require("dotenv").config();
const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const bcrypt = require("bcrypt");
const { OAuth2Client } = require("google-auth-library");
const mysql = require("mysql2/promise");

const app = express();
const PORT = process.env.PORT || 8080;
const CLIENT_ID = process.env.CLIENT_ID;

// Konfigurasi koneksi database
const db = mysql.createPool({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
});

const client = new OAuth2Client(CLIENT_ID);

app.use(cors());
app.use(bodyParser.json());

// Register Manual 
app.post("/register", async (req, res) => {
  const { name, email, password } = req.body;

  if (!name || !email || !password) {
    return res.status(400).json({ error: "All fields are required" });
  }

  try {
    const [rows] = await db.query("SELECT * FROM users WHERE email = ?", [email]);
    if (rows.length > 0) {
      return res.status(400).json({ error: "Email already registered" });
    }

    const hashedPassword = await bcrypt.hash(password, 10);
    await db.query("INSERT INTO users (name, email, password, type) VALUES (?, ?, ?, 'manual')", [name, email, hashedPassword]);
    res.status(201).json({ message: "User registered successfully" });
  } catch (error) {
    res.status(500).json({ error: "Database error" });
  }
});

//Register dengan Google
app.post("/register/google", async (req, res) => {
  const { token } = req.body;

  try {
    const ticket = await client.verifyIdToken({ idToken: token, audience: CLIENT_ID });
    const payload = ticket.getPayload();
    const { email, name } = payload;

    const [rows] = await db.query("SELECT * FROM users WHERE email = ?", [email]);
    if (rows.length > 0) {
      return res.status(400).json({ error: "Email already registered" });
    }

    await db.query("INSERT INTO users (name, email, type) VALUES (?, ?, 'google')", [name, email]);
    res.status(201).json({ message: "Google user registered successfully" });
  } catch (error) {
    res.status(500).json({ error: "Google token error" });
  }
});

//Login Manual
app.post("/login", async (req, res) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ error: "Email and password are required" });
  }

  try {
    const [rows] = await db.query("SELECT * FROM users WHERE email = ? AND type = 'manual'", [email]);
    if (rows.length === 0) {
      return res.status(400).json({ error: "Invalid email or password" });
    }

    const user = rows[0];
    const isPasswordValid = await bcrypt.compare(password, user.password);
    if (!isPasswordValid) {
      return res.status(400).json({ error: "Invalid email or password" });
    }

    res.status(200).json({ message: "Login successful", user: { name: user.name, email: user.email } });
  } catch (error) {
    res.status(500).json({ error: "Database error" });
  }
});

//Login dengan Google
app.post("/login/google", async (req, res) => {
  const { token } = req.body;

  try {
    const ticket = await client.verifyIdToken({ idToken: token, audience: CLIENT_ID });
    const payload = ticket.getPayload();
    const { email, name } = payload;

    const [rows] = await db.query("SELECT * FROM users WHERE email = ? AND type = 'google'", [email]);
    if (rows.length === 0) {
      return res.status(400).json({ error: "Google user not registered" });
    }

    res.status(200).json({ message: "Google login successful", user: { name: rows[0].name, email: rows[0].email } });
  } catch (error) {
    res.status(500).json({ error: "Google token error" });
  }
});

// Jalankan server
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
