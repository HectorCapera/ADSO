import React, { useState } from 'react';
import { CitasPage } from './pages/CitasPage';
import { LoginPage } from './pages/LoginPage';

function App() {
  // Estado de sesión. 'null' indica que no hay usuario autenticado (Acceso denegado a CitasPage)
  const [usuario, setUsuario] = useState(null); 

  /**
   * Callback que se ejecuta cuando el LoginPage confirma credenciales válidas.
   * Actualiza el estado principal con la información del usuario.
   */
  const handleLoginSuccess = (datosUsuario) => {
    setUsuario(datosUsuario); 
  };

  /**
   * Limpia el estado del usuario, provocando que la interfaz regrese al Login.
   */
  const handleLogout = () => {
    setUsuario(null); 
  };

  return (
    <div style={{ backgroundColor: '#f0f2f5', minHeight: '100vh' }}>
      
      {/* RENDERIZADO CONDICIONAL (Protección de Rutas) */}
      {!usuario ? (
        // Caso 1: Usuario no autenticado -> Renderiza Login
        <LoginPage onLoginSuccess={handleLoginSuccess} />
      ) : (
        // Caso 2: Usuario autenticado -> Renderiza la Aplicación Principal
        <>
          {/* Barra superior simple con información de sesión */}
          <div style={{ 
              padding: '10px 20px', 
              textAlign: 'right', 
              background: 'white', 
              boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
              display: 'flex',
              justifyContent: 'flex-end',
              alignItems: 'center',
              gap: '15px'
          }}>
            <span>Bienvenido, <b>{usuario.usuario}</b> <small>({usuario.rol})</small></span>
            
            <button 
              onClick={handleLogout} 
              style={{ 
                padding: '8px 16px', 
                cursor: 'pointer', 
                backgroundColor: '#d32f2f', 
                color: 'white', 
                border: 'none', 
                borderRadius: '4px',
                fontWeight: 'bold'
              }}
            >
              Cerrar Sesión
            </button>
          </div>
          
          {/* Módulo de Gestión */}
          <CitasPage />
        </>
      )}

    </div>
  );
}

export default App;