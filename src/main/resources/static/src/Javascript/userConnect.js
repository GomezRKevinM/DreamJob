document.addEventListener('DOMContentLoaded', () => {
    const navList = document.querySelector(".nav-links ul");
    const loginButton = navList.querySelector(".btn-cta");

    const isLoggedIn = true;

    if (isLoggedIn) {
        loginButton.closest("li").remove()

        const userMenu = document.createElement("li");
        userMenu.classList.add("user-menu");

        userMenu.innerHTML = `
            <button id="userButton" class="user-btn">
                <span id="userName">Nombre</span>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" width="24" height="24">
                    <path d="M6.22 8.47a.75.75 0 0 1 1.06 0L12 13.19l4.72-4.72a.75.75 0 0 1 1.06 1.06l-5.25 5.25a.75.75 0 0 1-1.06 0L6.22 9.53a.75.75 0 0 1 0-1.06Z"/>
                </svg>
                </button>
                <ul id="userDropdown" class="dropdown hidden">
                    <li><a href="#">Configuración</a></li>
                    <li><a href="#" id="logoutBtn">Cerrar Sessión</a></li>
                </ul>
            `;

        navList.appendChild(userMenu);
    }
})