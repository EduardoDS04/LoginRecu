package com.example.loginrecu

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.loginrecu.model.User

// Define una clase DialogFragment para registrar un nuevo usuario.
class RegisterDialogFragment(private val onRegister: (User) -> Unit) : DialogFragment() {

    // Se llama al crear el diálogo.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Infla la vista personalizada para el de registro de usuario.
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_new_user, null)
        val nameEditText = view.findViewById<EditText>(R.id.txtViewName)
        val usernameEditText = view.findViewById<EditText>(R.id.txtViewUser)
        val passwordEditText = view.findViewById<EditText>(R.id.txtViewPassword)
        val emailEditText = view.findViewById<EditText>(R.id.txtViewEmail)

        return AlertDialog.Builder(requireContext()).apply {
            setView(view) // Establece la vista personalizada
            // Configura el botón "Register", que recoge los datos ingresados
            setPositiveButton("Register") { _, _ ->
                // Crea una instancia de User con los datos ingresados.
                val user = User(
                    name = nameEditText.text.toString(),
                    user = usernameEditText.text.toString(),
                    password = passwordEditText.text.toString(),
                    email = emailEditText.text.toString()
                )
                // Ejecuta el callback pasando el nuevo usuario.
                onRegister(user)
            }

            setNegativeButton("Cancel", null)
        }.create()
    }

    // Método estático para crear una nueva instancia del fragmento, proporcionando un callback.
    companion object {
        fun newInstance(onRegister: (User) -> Unit): RegisterDialogFragment {
            return RegisterDialogFragment(onRegister)
        }
    }
}
