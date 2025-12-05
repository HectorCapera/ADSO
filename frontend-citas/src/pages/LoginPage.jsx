import React, { useState } from 'react';
import { loginUsuario, registrarUsuario } from '../services/AuthService';
import { Container, Paper, TextField, Button, Typography, Box, Link } from '@mui/material';
import Swal from 'sweetalert2';

// Recibimos 'onLoginSuccess' como prop para comunicar al componente padre (App.jsx) que el login fue exitoso
export const LoginPage = ({ onLoginSuccess }) => {
    
    // State para alternar dinámicamente entre la vista de Login y la de Registro
    const [isLogin, setIsLogin] = useState(true); 
    
    // State único para manejar todos los campos del formulario
    // Se inicializa con valores vacíos para convertir los inputs en componentes controlados
    const [formData, setFormData] = useState({
        nombre: "",
        email: "",
        password: "",
        rol: "PACIENTE" // Rol por defecto asignado a nuevos registros
    });

    /**
     * Manejador genérico para capturar lo que el usuario escribe.
     * Utiliza la desestructuración [e.target.name] para actualizar el campo correcto en el estado.
     */
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    /**
     * Gestión del envío del formulario.
     * Discrimina si se debe ejecutar Login o Registro basándose en el estado 'isLogin'.
     */
    const handleSubmit = async (e) => {
        e.preventDefault(); // Evita la recarga automática de la página (comportamiento default de HTML)
        
        try {
            if (isLogin) {
                // --- FLUJO DE INICIO DE SESIÓN ---
                const resp = await loginUsuario({ 
                    email: formData.email, 
                    password: formData.password 
                });
                
                // Feedback visual inmediato al usuario
                Swal.fire("¡Bienvenido!", `Hola ${resp.data.usuario}`, "success");
                
                // Elevación de estado: Enviamos los datos del usuario a App.jsx para desbloquear la vista principal
                onLoginSuccess(resp.data); 
                
            } else {
                // --- FLUJO DE REGISTRO ---
                await registrarUsuario(formData);
                
                Swal.fire("¡Éxito!", "Cuenta creada. Por favor inicia sesión.", "success");
                
                // UX: Redirigir automáticamente al usuario al formulario de Login tras registrarse
                setIsLogin(true); 
            }
        } catch (error) {
            console.error("Error en autenticación:", error);
            // Manejo de errores: Muestra el mensaje enviado por el Backend (ej: "Correo ya existe") o uno genérico
            Swal.fire("Error de Autenticación", error.response?.data || "Verifique sus credenciales", "error");
        }
    };

    return (
        <Container maxWidth="xs" sx={{ mt: 8 }}>
            {/* Paper crea el efecto de tarjeta elevada característico de Material UI */}
            <Paper elevation={3} sx={{ p: 4, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                
                <Typography component="h1" variant="h5" color="primary" sx={{ mb: 3, fontWeight: 'bold' }}>
                    {isLogin ? 'Iniciar Sesión' : 'Crear Cuenta'}
                </Typography>

                <form onSubmit={handleSubmit} style={{ width: '100%' }}>
                    
                    {/* Renderizado Condicional: El campo 'Nombre' solo existe en modo Registro */}
                    {!isLogin && (
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Nombre Completo"
                            name="nombre"
                            value={formData.nombre}
                            onChange={handleChange}
                        />
                    )}

                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        label="Correo Electrónico"
                        name="email"
                        type="email"
                        value={formData.email}
                        onChange={handleChange}
                    />

                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        label="Contraseña"
                        name="password"
                        type="password" // Oculta los caracteres por seguridad
                        value={formData.password}
                        onChange={handleChange}
                    />

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2, py: 1.5, fontWeight: 'bold' }}
                    >
                        {isLogin ? 'Ingresar' : 'Registrarse'}
                    </Button>

                    <Box sx={{ textAlign: 'center' }}>
                        {/* Botón tipo enlace para alternar entre modos sin recargar */}
                        <Link 
                            component="button" 
                            variant="body2" 
                            type="button"
                            onClick={() => setIsLogin(!isLogin)}
                        >
                            {isLogin ? "¿No tienes cuenta? Regístrate aquí" : "¿Ya tienes cuenta? Inicia Sesión"}
                        </Link>
                    </Box>
                </form>
            </Paper>
        </Container>
    );
};