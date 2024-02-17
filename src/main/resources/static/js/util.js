// Obtener el botón de cierre de sesión
const botonCerrarSesion = document.getElementById('boton-cerrar-sesion');

// Añadir un listener al botón
botonCerrarSesion.addEventListener('click', function() {
  // Eliminar el token JWT
  localStorage.removeItem('token');
  // Redirigir al usuario a index.html
window.location.href = 'index.html';
})