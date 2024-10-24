package com.example.testnach

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log


class LoginUsuario : Fragment() {

    private lateinit var auth: FirebaseAuth
    var emailEditText : EditText? = null
    var passwordEditText: EditText? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_usuario, container, false)
       
                 
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEditText = view.findViewById<EditText>(R.id.etEmail)
        passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnSinLogin = view.findViewById<Button>(R.id.btnSinLogin)


        btnLogin.setOnClickListener {
            val email = emailEditText!!.text.toString().trim()
            val password = passwordEditText!!.text.toString().trim()

            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(requireContext(), "Por favor, ingresa tu email y contraseña", Toast.LENGTH_SHORT).show()
             else
                signInWithFirebase(email, password)

        }


        btnSinLogin.setOnClickListener {
            val loginFragment = PokemonListFragment()
            parentFragmentManager.beginTransaction() // Usar parentFragmentManager si es un fragmento hijo
                .replace(R.id.fragment_container, loginFragment)
                .addToBackStack(null) // Añadir a la pila de retroceso si quieres poder volver
                .commit()
        }

    }

    private fun signInWithFirebase(email: String, password: String) {
        try
        {
            FirebaseApp.initializeApp(requireContext())
            Toast.makeText(requireContext(), "Entra2", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    Toast.makeText(requireContext(), "Entra3", Toast.LENGTH_SHORT).show()
                    if (task.isSuccessful) {
                        // Navegar a la pantalla principal
                        val loginFragment = PokemonListFragment()
                        parentFragmentManager.beginTransaction() // Usar parentFragmentManager si es un fragmento hijo
                            .replace(R.id.fragment_container, loginFragment)
                            .addToBackStack(null) // Añadir a la pila de retroceso si quieres poder volver
                            .commit()
                    } else {
                        Toast.makeText(requireContext(), "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        catch (ex: Exception)
        {
            Toast.makeText(requireContext(), ex.message.toString(), Toast.LENGTH_SHORT).show()

        }
    }
}