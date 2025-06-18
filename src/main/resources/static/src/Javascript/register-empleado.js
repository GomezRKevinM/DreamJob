// empleado-wizard.js

let currentStep = 0;
const formSteps = document.querySelectorAll('.form-step');
const stepItems = document.querySelectorAll('.step-item');
const btnPrev = document.querySelector('.btn-prev');
const btnNext = document.querySelector('.btn-next');
const btnSubmit = document.querySelector('.btn-submit');
const form = document.getElementById('employeeProfileForm');

// Índices para campos dinámicos (reutilizados del script anterior)
let habilidadesIndex = 1; // Ya tenemos el índice 0 inicial en el HTML
let idiomasIndex = 1;     // Ya tenemos el índice 0 inicial en el HTML
let experienciaIndex = 0;
let educacionIndex = 0;
let referenciaIndex = 0;

// Inicializa las funciones de añadir campos dinámicos al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    showStep(currentStep); // Muestra el primer paso al cargar
    // Inicializar un campo de habilidad y idioma al cargar la página si el contenedor está vacío
    if (document.getElementById('habilidades-container').children.length === 0) {
        addDynamicField('habilidades-container', 'habilidades', 'habilidad');
    }
    if (document.getElementById('idiomas-container').children.length === 0) {
        addDynamicField('idiomas-container', 'idiomas', 'idioma');
    }
});


function showStep(stepNumber) {
    // Oculta todos los pasos
    formSteps.forEach((step, index) => {
        step.classList.remove('active');
        if (index === stepNumber) {
            step.classList.add('active'); // Muestra el paso actual
        }
    });

    // Actualiza el indicador de progreso
    stepItems.forEach((item, index) => {
        item.classList.remove('active');
        if (index === stepNumber) {
            item.classList.add('active');
        }
    });

    // Controla la visibilidad de los botones de navegación
    btnPrev.style.display = (stepNumber === 0) ? 'none' : 'block';
    btnNext.style.display = (stepNumber === formSteps.length - 1) ? 'none' : 'block';
    btnSubmit.style.display = (stepNumber === formSteps.length - 1) ? 'block' : 'none';

    // Si estamos en el último paso (Revisar y Guardar), generar el resumen
    if (stepNumber === formSteps.length - 1) {
        generateReviewSummary();
    }
}

function validateStep(stepNumber) {
    const currentFormStep = formSteps[stepNumber];
    const inputs = currentFormStep.querySelectorAll('input[required], select[required], textarea[required]');
    let isValid = true;

    inputs.forEach(input => {
        if (!input.value.trim() && input.type !== 'checkbox') { // Excluye checkboxes de la validación trim
            input.style.borderColor = 'red'; // Resaltar el campo inválido
            isValid = false;
        } else {
            input.style.borderColor = '#ddd'; // Restaurar el borde
        }
    });

    // Validaciones específicas si las necesitas
    // Puedes añadir más validaciones aquí para cada paso
    // Ej: validación de fechas, tamaños de archivo, etc.
    return isValid;
}


btnNext.addEventListener('click', () => {
    if (validateStep(currentStep)) {
        currentStep++;
        showStep(currentStep);
    } else {
        alert('Por favor, completa todos los campos requeridos antes de continuar.');
    }
});

btnPrev.addEventListener('click', () => {
    currentStep--;
    showStep(currentStep);
});

// Event listener para los items del sidebar (para navegar directamente a un paso)
stepItems.forEach(item => {
    item.addEventListener('click', () => {
        const targetStep = parseInt(item.dataset.step);
        // Puedes añadir validación aquí si quieres que el usuario no salte pasos sin completar los anteriores
        // if (targetStep <= currentStep) { // Solo permite ir a pasos anteriores o el actual
        showStep(targetStep);
        currentStep = targetStep;
        // } else {
        //    alert('Por favor, completa los pasos anteriores para avanzar.');
        // }
    });
});


/**
 * Función para recolectar todos los datos del formulario en un objeto estructurado.
 * Esto facilitará el envío de datos a un backend y la generación del resumen.
 * @returns {Object} Un objeto con todos los datos del formulario.
 */
function collectFormData() {
    const data = {};
    const formData = new FormData(form);

    for (let [name, value] of formData.entries()) {
        // Ignorar el archivo CV por ahora para el resumen (ya lo tienes en el HTML)
        if (name === 'cvFile') continue;

        // Manejar campos dinámicos (arrays de objetos)
        // Por ejemplo: habilidades[0].habilidad, experienciaLaboral[0].nombreEmpresa
        const match = name.match(/(\w+)\[(\d+)\]\.(\w+)/);
        if (match) {
            const arrayName = match[1]; // ej. 'habilidades'
            const index = parseInt(match[2]); // ej. 0
            const fieldName = match[3]; // ej. 'habilidad'

            if (!data[arrayName]) {
                data[arrayName] = [];
            }
            if (!data[arrayName][index]) {
                data[arrayName][index] = {};
            }
            data[arrayName][index][fieldName] = value;
        } else {
            // Manejar campos normales
            data[name] = value;
        }
    }

    // Manejar checkboxes que no envían valor si no están marcados
    // Recorremos los checkboxes para incluir 'false' si no están marcados
    form.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
        const match = checkbox.name.match(/(\w+)\[(\d+)\]\.(\w+)/);
        if (match) {
            const arrayName = match[1];
            const index = parseInt(match[2]);
            const fieldName = match[3];
            if (checkbox.name.includes('trabajaActualmente') || checkbox.name.includes('CursandoActualmente')) {
                if (!data[arrayName] || !data[arrayName][index]) {
                     // Esto debería ser creado ya por los otros campos, pero como fallback
                    if (!data[arrayName]) data[arrayName] = [];
                    if (!data[arrayName][index]) data[arrayName][index] = {};
                }
                data[arrayName][index][fieldName] = checkbox.checked ? 'Sí' : 'No';
            }
        } else {
            // Si es un checkbox normal (no dinámico), aunque no tienes ninguno en el HTML actual para el resumen
            data[checkbox.name] = checkbox.checked ? 'Sí' : 'No';
        }
    });


    return data;
}


// Función para generar el resumen en el último paso
function generateReviewSummary() {
    const summaryContainer = document.getElementById('review-summary');
    const formData = collectFormData(); // Recolecta todos los datos

    let summaryHtml = '<h4>Resumen de tu Información:</h4>';
    summaryHtml += '<div class="summary-details">'; // Contenedor para el estilo

    // Definir un orden y etiquetas amigables para los campos
    const personalFields = {
        nombre: 'Nombre',
        apellido: 'Apellido',
        telefono: 'Teléfono',
        direccion: 'Dirección',
        fechaNacimiento: 'Fecha de Nacimiento',
        tipoID: 'Tipo de Identificación',
        identificacion: 'Número de Identificación'
    };

    const professionalFields = {
        // cvFile: 'CV Subido', // El CV no se puede mostrar directamente, se puede indicar que fue subido
        habilidades: 'Habilidades',
        idiomas: 'Idiomas'
    };

    // --- Datos Personales ---
    summaryHtml += '<h5>Datos Personales:</h5><ul>';
    for (const key in personalFields) {
        if (formData[key]) {
            summaryHtml += `<li><strong>${personalFields[key]}:</strong> ${formData[key]}</li>`;
        }
    }
    summaryHtml += '</ul>';

    // --- Información Profesional ---
    summaryHtml += '<h5>Información Profesional:</h5><ul>';
    // Para CV, solo indicamos si se subió o no, ya que no podemos mostrar el contenido
    const cvInput = document.getElementById('cv_file');
    if (cvInput && cvInput.files.length > 0) {
        summaryHtml += `<li><strong>CV:</strong> ${cvInput.files[0].name} (Subido)</li>`;
    } else {
        summaryHtml += `<li><strong>CV:</strong> No se ha subido</li>`;
    }

    // Habilidades
    if (formData.habilidades && formData.habilidades.length > 0) {
        summaryHtml += `<li><strong>Habilidades:</strong></li><ul>`;
        formData.habilidades.forEach(h => {
            if (h.habilidad) {
                summaryHtml += `<li>- ${h.habilidad}</li>`;
            }
        });
        summaryHtml += `</ul>`;
    }

    // Idiomas
    if (formData.idiomas && formData.idiomas.length > 0) {
        summaryHtml += `<li><strong>Idiomas:</strong></li><ul>`;
        formData.idiomas.forEach(i => {
            if (i.idioma) {
                summaryHtml += `<li>- ${i.idioma}</li>`;
            }
        });
        summaryHtml += `</ul>`;
    }
    summaryHtml += '</ul>';


    // --- Experiencia Laboral ---
    if (formData.experienciaLaboral && formData.experienciaLaboral.length > 0) {
        summaryHtml += '<h5>Experiencia Laboral:</h5><ul>';
        formData.experienciaLaboral.forEach((exp, idx) => {
            summaryHtml += `<li><strong>Experiencia ${idx + 1}:</strong>`;
            summaryHtml += `<ul>`;
            if (exp.nombreEmpresa) summaryHtml += `<li>Empresa: ${exp.nombreEmpresa}</li>`;
            if (exp.paisEmpresa) summaryHtml += `<li>País: ${exp.paisEmpresa}</li>`;
            if (exp.modalidadDeTrabajo) summaryHtml += `<li>Modalidad: ${exp.modalidadDeTrabajo}</li>`;
            if (exp.cargo) summaryHtml += `<li>Cargo: ${exp.cargo}</li>`;
            if (exp.inicio) summaryHtml += `<li>Inicio: ${exp.inicio}</li>`;
            // 'trabajaActualmente' se maneja como 'Si' o 'No' en collectFormData para el resumen
            if (exp.trabajaActualmente === 'Sí') {
                summaryHtml += `<li>Trabajo actualmente aquí: Sí</li>`;
            } else if (exp.termino) {
                summaryHtml += `<li>Término: ${exp.termino}</li>`;
            }
            if (exp.JefeInmediato_Nombre) summaryHtml += `<li>Jefe Inmediato: ${exp.JefeInmediato_Nombre}</li>`;
            if (exp.JefeInmediato_Telefono) summaryHtml += `<li>Teléfono Jefe: ${exp.JefeInmediato_Telefono}</li>`;
            if (exp.funcion) summaryHtml += `<li>Función Principal: ${exp.funcion}</li>`;
            if (exp.detalles) summaryHtml += `<li>Detalles: ${exp.detalles}</li>`;
            summaryHtml += `</ul></li>`;
        });
        summaryHtml += '</ul>';
    }


    // --- Educación ---
    if (formData.educacion && formData.educacion.length > 0) {
        summaryHtml += '<h5>Educación:</h5><ul>';
        formData.educacion.forEach((edu, idx) => {
            summaryHtml += `<li><strong>Educación ${idx + 1}:</strong>`;
            summaryHtml += `<ul>`;
            if (edu.institucion) summaryHtml += `<li>Institución: ${edu.institucion}</li>`;
            if (edu.tipoInstituto) summaryHtml += `<li>Tipo de Instituto: ${edu.tipoInstituto}</li>`;
            if (edu.titulo) summaryHtml += `<li>Título / Carrera: ${edu.titulo}</li>`;
            if (edu.inicio) summaryHtml += `<li>Inicio: ${edu.inicio}</li>`;
            if (edu.CursandoActualmente === 'Sí') {
                summaryHtml += `<li>Cursando actualmente: Sí</li>`;
            } else if (edu.termino) {
                summaryHtml += `<li>Término: ${edu.termino}</li>`;
            }
            if (edu.pais) summaryHtml += `<li>País: ${edu.pais}</li>`;
            if (edu.departamento) summaryHtml += `<li>Departamento: ${edu.departamento}</li>`;
            if (edu.ciudad) summaryHtml += `<li>Ciudad: ${edu.ciudad}</li>`;
            summaryHtml += `</ul></li>`;
        });
        summaryHtml += '</ul>';
    }


    // --- Referencias Personales ---
    if (formData.referenciasPersonales && formData.referenciasPersonales.length > 0) {
        summaryHtml += '<h5>Referencias Personales:</h5><ul>';
        formData.referenciasPersonales.forEach((ref, idx) => {
            summaryHtml += `<li><strong>Referencia ${idx + 1}:</strong>`;
            summaryHtml += `<ul>`;
            if (ref.nombre) summaryHtml += `<li>Nombre: ${ref.nombre}</li>`;
            if (ref.telefono) summaryHtml += `<li>Teléfono: ${ref.telefono}</li>`;
            if (ref.Parentezco) summaryHtml += `<li>Parentezco / Relación: ${ref.Parentezco}</li>`;
            summaryHtml += `</ul></li>`;
        });
        summaryHtml += '</ul>';
    }

    summaryHtml += '</div>'; // Cierra summary-details
    summaryContainer.innerHTML = summaryHtml;
}


function addDynamicField(containerId, arrayName, fieldName) {
    const container = document.getElementById(containerId);
    let index;
    if (containerId === 'habilidades-container') {
        index = habilidadesIndex++;
    } else if (containerId === 'idiomas-container') {
        index = idiomasIndex++;
    }
    const newItemHtml = `
        <div class="item-entry">
            <button type="button" class="remove-item" onclick="removeDynamicField(this)">x</button>
            <div class="item-grid-container"> 
                <div>
                    <label for="${arrayName}[${index}].${fieldName}">${fieldName.charAt(0).toUpperCase() + fieldName.slice(1)}:</label>
                    <input type="text" name="${arrayName}[${index}].${fieldName}" required placeholder="Ej: ${fieldName === 'habilidad' ? 'Trabajo en equipo' : 'Francés'}">
                </div>
            </div>
        </div>
    `;
    container.insertAdjacentHTML('beforeend', newItemHtml);
}

// Función removeDynamicField se mantiene igual
function removeDynamicField(button) {
    button.closest('.item-entry').remove();
}


// --- MODIFICAR ESTAS FUNCIONES PARA ENVOLVER LOS CAMPOS EN .item-grid-container ---

function addExperienciaLaboral() {
    const container = document.getElementById('experiencia-laboral-container');
    const newItemHtml = `
        <div class="item-entry">
            <button type="button" class="remove-item" onclick="removeDynamicField(this)">x</button>
            <div class="item-grid-container">
                <div>
                    <label>Nombre Empresa:</label>
                    <input type="text" name="experienciaLaboral[${experienciaIndex}].nombreEmpresa" required placeholder="Ej: Microsoft">
                </div>
                <div>
                    <label>País Empresa:</label>
                    <input type="text" name="experienciaLaboral[${experienciaIndex}].paisEmpresa" placeholder="Ej: España">
                </div>
                <div>
                    <label>Modalidad de Trabajo:</label>
                    <select name="experienciaLaboral[${experienciaIndex}].modalidadDeTrabajo">
                        <option value="">Selecciona</option>
                        <option value="PRESENCIAL">Presencial</option>
                        <option value="REMOTO">Remoto</option>
                        <option value="HIBRIDO">Híbrido</option>
                    </select>
                </div>

                <div>
                    <label>Cargo:</label>
                    <input type="text" name="experienciaLaboral[${experienciaIndex}].cargo" required placeholder="Ej: Desarrollador Frontend">
                </div>
                <div>
                    <label>Fecha Inicio:</label>
                    <input type="date" name="experienciaLaboral[${experienciaIndex}].inicio" required>
                </div>
                <div>
                    <label id="termino-label-${experienciaIndex}">Fecha Término:</label>
                    <input type="date" name="experienciaLaboral[${experienciaIndex}].termino">
                </div>
                <div class="checkbox-group full-width-field">
                    <input type="checkbox" id="trabajaActual_exp${experienciaIndex}" name="experienciaLaboral[${experienciaIndex}].trabajaActualmente" value="true" onchange="toggleTerminoDate(this, 'experienciaLaboral[${experienciaIndex}].termino', 'termino-label-${experienciaIndex}')">
                    <label for="trabajaActual_exp${experienciaIndex}">¿Trabajo actualmente aquí?</label>
                </div>

                <div>
                    <label>Nombre Jefe Inmediato:</label>
                    <input type="text" name="experienciaLaboral[${experienciaIndex}].JefeInmediato_Nombre" placeholder="Ej: Juan Pérez">
                </div>
                <div> 
                    <label>Teléfono Jefe Inmediato:</label>
                    <input type="number" name="experienciaLaboral[${experienciaIndex}].JefeInmediato_Telefono" placeholder="Ej: +XX XXX XXXXXXX">
                </div>
                <div class="full-width-field"> 
                    <label>Función principal (una frase):</label>
                    <input type="text" name="experienciaLaboral[${experienciaIndex}].funcion" placeholder="Ej: Desarrollo de interfaces de usuario">
                </div>


                <div class="details-area full-width-field">
                    <label>Detalles (opcional):</label>
                    <textarea name="experienciaLaboral[${experienciaIndex}].detalles" placeholder="Describe tus responsabilidades y logros"></textarea>
                </div>
            </div> 
        </div>
    `;
    container.insertAdjacentHTML('beforeend', newItemHtml);
    experienciaIndex++;
}

function addEducacion() {
    const container = document.getElementById('educacion-container');
    const newItemHtml = `
        <div class="item-entry">
            <button type="button" class="remove-item" onclick="removeDynamicField(this)">x</button>
            <div class="item-grid-container"> 
                <div>
                    <label>Institución:</label>
                    <input type="text" name="educacion[${educacionIndex}].institucion" required placeholder="Ej: Universidad Nacional">
                </div>
                <div>
                    <label>Tipo de Instituto:</label>
                    <select name="educacion[${educacionIndex}].tipoInstituto" required>
                        <option value="">Selecciona</option>
                        <option value="Colegio">Colegio</option>
                        <option value="Seminario">Seminario</option>
                        <option value="Universidad">Universidad</option>
                        <option value="Bootcamp">Bootcamp</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>
                <div>
                    <label>Título / Carrera:</label>
                    <input type="text" name="educacion[${educacionIndex}].titulo" required placeholder="Ej: Ingeniería de Sistemas">
                </div>

                <div>
                    <label>Fecha Inicio:</label>
                    <input type="date" name="educacion[${educacionIndex}].inicio" required>
                </div>
                <div>
                    <label id="cursando-label-${educacionIndex}">Fecha Término:</label>
                    <input type="date" name="educacion[${educacionIndex}].termino">
                </div>
                <div class="checkbox-group full-width-field">
                    <input type="checkbox" id="cursandoActual_edu${educacionIndex}" name="educacion[${educacionIndex}].CursandoActualmente" value="true" onchange="toggleTerminoDate(this, 'educacion[${educacionIndex}].termino', 'cursando-label-${educacionIndex}')">
                    <label for="cursandoActual_edu${educacionIndex}">¿Cursando actualmente?</label>
                </div>

                <div>
                    <label>País:</label>
                    <input type="text" name="educacion[${educacionIndex}].pais" placeholder="Ej: Colombia">
                </div>
                <div>
                    <label>Departamento:</label>
                    <input type="text" name="educacion[${educacionIndex}].departamento" placeholder="Ej: Antioquia">
                </div>
                <div>
                    <label>Ciudad:</label>
                    <input type="text" name="educacion[${educacionIndex}].ciudad" placeholder="Ej: Medellín">
                </div>
            </div> 
        </div>
    `;
    container.insertAdjacentHTML('beforeend', newItemHtml);
    educacionIndex++;
}

function addReferenciaPersonal() {
    const container = document.getElementById('referencias-personales-container');
    const newItemHtml = `
        <div class="item-entry">
            <button type="button" class="remove-item" onclick="removeDynamicField(this)">x</button>
            <div class="item-grid-container"> 
                <div>
                    <label>Nombre:</label>
                    <input type="text" name="referenciasPersonales[${referenciaIndex}].nombre" required placeholder="Ej: María García">
                </div>
                <div>
                    <label>Teléfono:</label>
                    <input type="number" name="referenciasPersonales[${referenciaIndex}].telefono" required placeholder="Ej: +XX XXX XXXXXXX">
                </div>
                <div class="full-width-field">
                    <label>Parentezco / Relación:</label>
                    <input type="text" name="referenciasPersonales[${referenciaIndex}].Parentezco" placeholder="Ej: Amiga, Ex-compañera">
                </div>
            </div> 
        </div>
    `;
    container.insertAdjacentHTML('beforeend', newItemHtml);
    referenciaIndex++;
}

function toggleTerminoDate(checkbox, fieldName, labelId) {
    const inputField = document.querySelector(`input[name="${fieldName}"]`);
    const labelField = document.getElementById(labelId);
    if (inputField) {
        inputField.disabled = checkbox.checked;
        if (checkbox.checked) {
            inputField.value = ''; 
            if (labelField) labelField.textContent = 'Fecha Término (Cursando actualmente)';
        } else {
            if (labelField) labelField.textContent = 'Fecha Término:';
        }
    }
}