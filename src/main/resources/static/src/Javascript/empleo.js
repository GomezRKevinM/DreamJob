document.addEventListener('DOMContentLoaded', () => {
    const jobCards = document.querySelectorAll('.job-card');
    const jobDetailsPanel = document.querySelector('.job-details-panel');

    const jobData = {
        "1": {
            title: "Desarrollador Frontend",
            salary: "$3.000.000 /mes",
            company: "Tech Solutions Inc.",
            location: "Medellín, Colombia",
            modalidad: "Remoto",
            contrato: "Tiempo completo",
            experiencia: "2 años",
            description: "Buscamos un desarrollador Frontend experimentado con React/Vue y un buen manejo de HTML, CSS y JavaScript. Trabajarás en proyectos innovadores y un equipo dinámico.",
            requirements: [
                "Dominio de JavaScript (ES6+), HTML5, CSS3",
                "Experiencia con React.js o Vue.js (o Angular)",
                "Conocimiento de preprocesadores CSS (Sass/Less)",
                "Experiencia con Git y control de versiones",
                "Inglés intermedio"
            ]
        },
        "2": {
            title: "Diseñador UI/UX",
            salary: "$2.800.000 /mes",
            company: "Creative Designs Studio",
            location: "Bogotá, Colombia",
            modalidad: "Híbrido",
            contrato: "Tiempo completo",
            experiencia: "1 año",
            description: "Únete a nuestro equipo para crear experiencias de usuario excepcionales.",
            requirements: [
                "Portafolio comprobable de proyectos UI/UX",
                "Dominio de herramientas de diseño (Figma, Sketch, Adobe XD)",
                "Metodologías de diseño centrado en el usuario",
                "Wireframes, prototipos y user flows",
                "Habilidades de comunicación"
            ]
        },
        "3": {
            title: "Soporte Técnico",
            salary: "$2.200.000 /mes",
            company: "Global Tech Services",
            location: "Cali, Colombia",
            modalidad: "Presencial",
            contrato: "Prácticas",
            experiencia: "0 años",
            description: "Oportunidad para jóvenes talentos sin experiencia.",
            requirements: [
                "Estudiante o recién egresado de carreras afines",
                "Proactividad y ganas de aprender",
                "Resolución de problemas de software/hardware",
                "Buena comunicación y atención al cliente"
            ]
        },
        "4": {
            title: "Analista de Datos",
            salary: "$4.000.000 /mes",
            company: "Data Insights Corp.",
            location: "Barranquilla, Colombia",
            modalidad: "Remoto",
            contrato: "Medio tiempo",
            experiencia: "3 años",
            description: "Buscamos un analista de datos para procesar, limpiar e interpretar grandes conjuntos de datos.",
            requirements: [
                "Experiencia con SQL y bases de datos",
                "Visualización de datos (Tableau, Power BI)",
                "Python o R para análisis de datos",
                "Comunicación efectiva",
                "Pensamiento analítico"
            ]
        }
    };

    function displayJobDetails(jobId) {
        const job = jobData[jobId];
        if (!job) return;

        const html = `
            <button id="closeDetailsMobile" class="btn-close-mobile">← Volver a ofertas</button>
            <div class="job-details-content">
                <h2>${job.title}</h2>
                <p><strong>Empresa:</strong> ${job.company}</p>
                <p><strong>Ubicación:</strong> ${job.location}</p>
                <p><strong>Modalidad:</strong> ${job.modalidad}</p>
                <p><strong>Contrato:</strong> ${job.contrato}</p>
                <p><strong>Experiencia:</strong> ${job.experiencia}</p>
                <h3>Descripción del Puesto</h3>
                <p>${job.description}</p>
                <h3>Requisitos</h3>
                <ul>${job.requirements.map(r => `<li>${r}</li>`).join('')}</ul>
                <button class="btn-apply">Aplicar</button>
            </div>
        `;

        const isMobile = window.innerWidth < 1024;
        const jobList = document.querySelector('.job-listing-container');
        const mobilePanel = document.getElementById('jobDetailsMobile');
        const desktopPanel = document.querySelector('.job-details-panel');

        if (isMobile) {
            // Móvil o tablet
            jobList.style.display = 'none';
            mobilePanel.innerHTML = html;
            mobilePanel.classList.add('show');

            document.getElementById('closeDetailsMobile').addEventListener('click', () => {
                mobilePanel.classList.remove('show');
                mobilePanel.innerHTML = '';
                jobList.style.display = 'block';
            });

            window.scrollTo({ top: 0, behavior: 'smooth' });
        } else {
            // Escritorio
            desktopPanel.innerHTML = html;
        }
    }


    jobCards.forEach(card => {
        card.addEventListener('click', () => {
            jobCards.forEach(jc => jc.classList.remove('active'));
            card.classList.add('active');

            const jobId = card.dataset.jobId;
            displayJobDetails(jobId);
        });
    });

    if (jobCards.length > 0) {
        jobCards[0].click();
    }
});
