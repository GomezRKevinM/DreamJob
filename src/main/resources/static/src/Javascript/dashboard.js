function mostrarSeccion(id) {
  document.querySelectorAll('.seccion-dashboard').forEach(seccion => {
    seccion.style.display = 'none';
  });
  document.getElementById(id).style.display = 'block';
}


