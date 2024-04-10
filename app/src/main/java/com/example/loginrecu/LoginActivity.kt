package com.example.loginrecu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginrecu.model.User

// La actividad principal que se muestra al usuario para el inicio de sesión.
class LoginActivity : AppCompatActivity() {
    // Usuario registrado actualmente en la aplicación.
    private var registeredUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout para esta actividad.
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.username_login)
        val passwordEditText = findViewById<EditText>(R.id.password_login)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        // Establece un listener para el evento de clic en el botón de inicio de sesion.
        loginButton.setOnClickListener {
            // Recupera el nombre de usuario y la contraseña.
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verifica si las credenciales ingresadas son válidas
            // un usuario predeterminado o contra el usuario registrado.
            if ((username == getString(R.string.default_user) && password == getString(R.string.default_password)) ||
                (username == registeredUser?.user && password == registeredUser?.password)) {
                // Crea un Intent para iniciar MainActivity y pasa un mensaje de bienvenida.
                val intent = Intent(this, MainActivity::class.java).also {
                    it.putExtra("EXTRA_MESSAGE", "Bienvenido, $username")
                }
                startActivity(intent)
            } else {
                // Muestra un mensaje de error si las credenciales son incorrectas.
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        }

        // Establece un listener para el evento de clic en el botón de registro.
        registerButton.setOnClickListener {
            // Muestra el diálogo de registro y define un callback para cuando se registre un nuevo usuario.
            val dialog = RegisterDialogFragment { user ->
                // Actualiza la referencia del usuario registrado con los nuevos valores.
                registeredUser = user
                // Muestra un mensaje confirmando el registro del usuario.
                Toast.makeText(this, "Usuario registrado: ${user.name}", Toast.LENGTH_LONG).show()
            }
            dialog.show(supportFragmentManager, "registerDialog")
        }
    }
}
