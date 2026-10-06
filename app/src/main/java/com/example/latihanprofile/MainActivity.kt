package com.example.latihanprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvRole: TextView

    private val roleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedRole = result.data?.getStringExtra("SELECTED_ROLE")
            if (!selectedRole.isNullOrEmpty()) {
                tvRole.text = selectedRole
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _btnEmail = findViewById<LinearLayout>(R.id.btnEmail)
        val _btnPhone = findViewById<LinearLayout>(R.id.btnPhone)
        val _btnSchool = findViewById<LinearLayout>(R.id.btnSchool)
        tvRole = findViewById(R.id.tvRoleAcc)
        val _btnRole = findViewById<LinearLayout>(R.id.btnRole)

        _btnEmail.setOnClickListener {
            val _emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:sarah@school.edu")
            }
            startActivity(_emailIntent)
        }

        _btnPhone.setOnClickListener {
            val _phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+15559876547")
            }
            startActivity(_phoneIntent)
        }

        _btnSchool.setOnClickListener {
            val locationName = "University of Oxford"
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationName))

            val _mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            _mapIntent.setPackage("com.google.android.apps.maps")

            if (_mapIntent.resolveActivity(packageManager) != null) {
                startActivity(_mapIntent)
            } else {
                val _webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(locationName))
                )

                try {
                    startActivity(_webIntent)
                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Tidak ada aplikasi untuk membuka peta",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        _btnRole.setOnClickListener {
            val _roleIntent = Intent(this@MainActivity, RoleActivity::class.java)
            roleLauncher.launch(_roleIntent)
        }

        val _switchPush = findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switchPush)
        _switchPush.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Notifikasi Push Aktif", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi Push NonAktif", Toast.LENGTH_SHORT).show()
            }
        }

        val _switchEmail = findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switchEmail)
        _switchEmail.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Email Notifikasi Aktif", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Email Notifikasi NonAktif", Toast.LENGTH_SHORT).show()
            }
        }
    }
}