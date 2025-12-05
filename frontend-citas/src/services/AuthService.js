import axios from "axios";

// URL base del API Gateway o Controlador de Autenticación en el Backend
// Se define como constante para facilitar cambios de entorno (Desarrollo/Producción)
const API_URL = "http://localhost:8090/api/auth";

/**
 * Envía una petición POST para validar las credenciales del usuario.
 * @param {Object} credenciales - Objeto con { email, password }
 * @returns {Promise} Respuesta del servidor con el token o datos del usuario.
 */
export const loginUsuario = async (credenciales) => {
    // Axios serializa automáticamente el objeto a JSON
    return await axios.post(`${API_URL}/login`, credenciales);
};

/**
 * Envía una petición POST para crear un nuevo usuario en la base de datos.
 * @param {Object} datosUsuario - Objeto con { nombre, email, password, rol }
 * @returns {Promise} Confirmación de creación (HTTP 201).
 */
export const registrarUsuario = async (datosUsuario) => {
    return await axios.post(`${API_URL}/registro`, datosUsuario);
};