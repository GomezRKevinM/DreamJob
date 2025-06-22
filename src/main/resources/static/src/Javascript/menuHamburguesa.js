document.addEventListener('DOMContentLoaded', function() {
    const hamburgerMenu = document.querySelector('.hamburger-menu');
    const navLinks = document.querySelector('.nav-links');
    const subsections = document.querySelectorAll('.nav-links .subsections, .nav-links .btn-cta');

    hamburgerMenu.addEventListener('click', function() {
        navLinks.classList.toggle('active');
        hamburgerMenu.classList.toggle('active');
    });

    subsections.forEach(link => {
        link.addEventListener('click', function() {
            if (navLinks.classList.contains('active')) { 
                navLinks.classList.remove('active');
                hamburgerMenu.classList.remove('active');
            }
        });
    });
});

console.warn("Imnobodyc and Fierzaa it was here")
