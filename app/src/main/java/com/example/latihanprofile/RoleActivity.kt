package com.example.latihanprofile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_role)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _btnAdmin = findViewById<Button>(R.id.btnRoleAdmin)
        val _btnTeacher = findViewById<Button>(R.id.btnRoleTeacher)
        val _btnStudent = findViewById<Button>(R.id.btnRoleStudent)

        _btnAdmin.setOnClickListener { selectRole("Admin") }
        _btnTeacher.setOnClickListener { selectRole("Teacher") }
        _btnStudent.setOnClickListener { selectRole("Student") }
    }
    private fun selectRole(selectedRole: String) {
        val resultIntent = Intent().apply {
            putExtra("SELECTED_ROLE", selectedRole)
        }

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}